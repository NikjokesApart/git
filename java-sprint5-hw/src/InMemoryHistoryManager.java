import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int SIZE_OF_HISTORY = 10;
    final private Map<Integer, Node<Task>> tasksIdAndNodes = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;
    private int sizeOfCustomLinkedList = 0;

    @Override
    public void add(Task task) {

    }

    @Override
    public void remove(int id) {
    }

    private static class Node<Task>{
        private Task data;
        private Node<Task> prev;
        private Node<Task> next;


        private Node(Node<Task> prev,Task data, Node<Task> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(data, node.data) && Objects.equals(prev, node.prev) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, prev, next);
        }
    }

    /*private ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node<Task> node = head;
        while (node.next != null) {
            tasks.add((Task) node);
        }
        return tasks;
    }*/

    private void removeNode(Task id) {
        final Node<Task> node = tasksIdAndNodes.remove(id);
        if (node.prev == null) {
            node.next.prev = null;
        } else if (node.prev != null && node != null) {
            node.next.prev = null;
        }
    }


    private void linkLast (Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if(oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        ++sizeOfCustomLinkedList;
        tasksIdAndNodes.put(task.getTaskById(),tail);
    }

}


