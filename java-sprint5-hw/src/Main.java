import static enums.TaskStatus.*;

public class Main {
    public static void main(String[] args) {

        TaskManager manager = Managers.getDefault();

        Task task1 = new Task("Task 1", "Task 1 description", NEW);
        Task task2 = new Task("Task 2", "Task 2 description", IN_PROGRESS);
        final int taskId1 = manager.addNewTask(task1);
        final int taskId2 = manager.addNewTask(task2);

        Epic epic1 = new Epic("Epic 1", "Epic 1 description", NEW);
        Epic epic2 = new Epic("Epic 1", "Epic 1 description", NEW);
        final int epicId1 = manager.addNewEpic(epic1);
        final int epicId2 = manager.addNewEpic(epic2);

        Subtask subtask1 = new Subtask("Subtask 1-1", "Subtask decsription", NEW, epicId1);
        Subtask subtask2 = new Subtask("Subtask 2-1", "Subtask decsription", NEW, epicId2);
        Subtask subtask3 = new Subtask("Subtask 3-2", "Subtask decsription", DONE, epicId2);

        final Integer subtaskId1 = manager.addNewSubtask(subtask1);
        final Integer subtaskId2 = manager.addNewSubtask(subtask2);
        final Integer subtaskId3 = manager.addNewSubtask(subtask3);

        manager.updateTask(task1);
        manager.updateTask(task2);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subtask2);
        manager.updateSubtask(subtask3);
        System.out.println(manager.getHistory());

        manager.deleteSubtaskById(subtaskId1);
        manager.deleteSubtaskById(subtaskId2);


        //manager.deleteTaskById(taskId2);
        manager.deleteAllEpics();
        manager.deleteAllTasks();
    }
}