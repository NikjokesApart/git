import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    int addNewTask(Task task);

    int addNewEpic(Epic epic);

    Integer addNewSubtask(Subtask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateEpicStatus(Epic epic, ArrayList<Subtask> subtasksByEpicId);

    void updateSubtask(Subtask subtask);

    void deleteEpicById(int id);

    void deleteSubtaskById(int id);

    void deleteTaskById(int id);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();
    List<Task> getHistory(){
        return historyManager.getHistory();
    }
    Task getTaskById(int taskId1);
}
