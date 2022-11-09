import enums.TaskStatus;

public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected TaskStatus status;

    public Task(String name, String description, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
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
}