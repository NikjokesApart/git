import enums.TaskStatus;

import java.util.List;

public abstract class Task {
    protected int id;
    protected String name;
    protected String description;
    protected TaskStatus status;

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    protected Task() {
    }

    public void setTaskById(int id) {
        this.id = id;
    }

    public int getTaskById() {
        return id;
    }

    public String setStatus(String status) {
        return status;
    }

    public abstract List<Task> getHistory();

    public abstract void add(Task task);
}