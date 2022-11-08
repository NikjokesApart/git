import enums.TaskStatus;

import java.util.ArrayList;

public class Epic extends Task {

    protected ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    @Override
    public String toString() {
        return "Subtask{" + "id=" + id + "name=" + name + "status=" + status + "description=" + description + "subtaskIds=[" + subtaskIds + "]}";
    }

    public void setSubtasksId(int subtaskId) {
        this.id=subtaskId;
    }
    public ArrayList<Integer> getSubtaskIds() {return subtaskIds;}
}