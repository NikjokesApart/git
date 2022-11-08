import enums.TaskStatus;

public class Subtask extends Task {
    protected int epicId;
    public Subtask(String name, String decsription, TaskStatus status, int epicId) {
        super(name, decsription, status);
        this.epicId = epicId;
    }
    @Override
    public String toString() {
        return "Subtask{" + "id=" + id + "epicId=" + epicId + "name=" + name + "status=" + status + "description=" + description + "}";
    }
     public int getEpicId() {
        return epicId;
    }
}