import java.util.ArrayList;
import java.util.List;

public abstract class InMemoryHistoryManager implements HistoryManager {
    @Override
    public List<Task> getHistory() {
        return null;
    }
    public List<Task> getHistory(){
        return InMemoryTaskManager.historyManager.getHistory();
    }

     @Override
    public void add(Task task) {

    }

    @Override
    public void remove(int id) {

    }
}
