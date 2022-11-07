import enums.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static enums.TaskStatus.*;

public class InMemoryTaskManager implements TaskManager {
    protected final HistoryManager historyManager = Managers.getDefaultHistory();
    private int id = 0;
    HashMap<Integer, Task> tasksHashMap = new HashMap<>();
    HashMap<Integer, Epic> epicHashMap = new HashMap<>();
    HashMap<Integer, Subtask> subtaskHashMap = new HashMap<>();

    public int takeId() {
        id++;
        return id;
    }

    @Override
    public int addNewTask(Task task) {
        int id = takeId();
        task.setTaskById(id);
        tasksHashMap.put(task.getTaskById(), task);
        return id;
    }

    @Override
    public int addNewEpic(Epic epic) {
        int id = takeId();
        epic.setTaskById(id);
        epicHashMap.put(epic.getTaskById(), epic);
        return id;
    }

    @Override
    public Integer addNewSubtask(Subtask subtask) {
        if (epicHashMap.containsKey(subtask.epicId)) {
            int id = takeId();
            subtask.setTaskById(id);
            subtaskHashMap.put(subtask.getTaskById(), subtask);
            setSubtasksId(subtask.getEpicId(), id);
            return id;
        } else {
            return null;
        }
    }

    @Override
    public void updateTask(Task task) {
        if (tasksHashMap.containsValue(task)) {
            tasksHashMap.put(task.id, task);
        } else {
            return;
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epicHashMap.containsValue(epic)) {
            epicHashMap.put(epic.id, epic);
        } else {
            return;
        }
    }

    @Override
    public void updateEpicStatus(Epic epic, ArrayList<Subtask> subtasksByEpicId) {
        if ((subtaskHashMap.size() == 0) || (isAllTasksHasThisStatus(NEW, subtasksByEpicId))) {
            epic.status = NEW;
        } else if (isAllTasksHasThisStatus(DONE, subtasksByEpicId)) {
            epic.status = DONE;
        } else {
            epic.status = IN_PROGRESS;
        }
        updateEpic(epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        if (subtaskHashMap.containsValue(subtask)) {
            if (!(subtask.epicId == 0)) {
                subtaskHashMap.put(subtask.id, subtask);
                Epic idEpic = getEpicById(subtask.getEpicId());
                updateEpicStatus(idEpic, getSubtasksByEpicId(idEpic.getTaskById()));
            } else {
                return;
            }
        }
    }

    @Override
    public void deleteEpicById(int id) {
        Epic deleteEpic = epicHashMap.get(id);
        epicHashMap.remove(id);
        ArrayList<Integer> subtaskIds = deleteEpic.getSubtaskIds();
        subtaskHashMap.remove(subtaskIds);
        historyManager.remove(id);
    }

    @Override
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtaskHashMap.remove(id);
        Epic idEpic = getEpicById(subtask.getEpicId());
        subtaskHashMap.remove(id);
        updateEpicStatus(idEpic, getSubtasksByEpicId(idEpic.getTaskById()));
        historyManager.remove(id);
    }

    @Override
    public void deleteTaskById(int id) {
        final Task task = tasksHashMap.remove(id);
        historyManager.remove(id);
        if (task == null) {
            return;
        }
    }

    @Override
    public void deleteAllTasks() {
        tasksHashMap.clear();
    }

    @Override
    public void deleteAllEpics() {
        epicHashMap.clear();
        deleteAllSubtasks();
    }

    @Override
    public void deleteAllSubtasks() {
        subtaskHashMap.clear();
        for (Epic servEpic : epicHashMap.values()) {
            if (servEpic.subtaskIds.equals(0)) {
                servEpic.setStatus("NEW");
            }
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    public Epic getEpicById(int id) {
        final Epic epic = epicHashMap.get(id);
        historyManager.add(epic);
                return epicHashMap.get(id);
    }

    public Subtask getSubtaskById(int id) {
        final Subtask subtask = subtaskHashMap.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    public Task getTaskById(int id) {
        final Task task = tasksHashMap.get(id);
        historyManager.add(task);
        return task;
    }

    public ArrayList<Subtask> getSubtasksByEpicId(int epicId) {
        ArrayList<Subtask> subtasksByEpicId = new ArrayList<>();
        for (Subtask subtask : subtaskHashMap.values()) {
            if (subtask.getTaskById() == epicId) {
                subtasksByEpicId.add(subtask);
            }
        }
        return subtasksByEpicId;
    }

    public boolean isAllTasksHasThisStatus(TaskStatus status, ArrayList<Subtask> subtasks) {
        boolean thisStatus = true;
        for (Subtask subtask : subtasks) {
            if (!subtask.status.equals(status)) {
                thisStatus = false;
            }
        }
        return thisStatus;
    }

    public void setSubtasksId(int epicId, int subtaskId) {
        Epic idEpic = getEpicById(epicId);
        idEpic.setSubtasksId(subtaskId);
        epicHashMap.put(idEpic.getTaskById(), idEpic);
    }
}