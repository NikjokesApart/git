import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    @Override
    public void add(Task task) {
    }

    @Override
    public void remove(int id) {
    }

    private static class Node<Task>{
        private Task task;
        private Node<Task> prev;
        private Node<Task> next;
        private Node<Task> head;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<Task> node = (Node<Task>) o;
            return sizeOfCustomLinkedList == node.sizeOfCustomLinkedList && Objects.equals(task, node.task) && Objects.equals(prev, node.prev) && Objects.equals(next, node.next) && Objects.equals(tasksIdAndNodes, node.tasksIdAndNodes) && Objects.equals(head, node.head) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(task, prev, next, tasksIdAndNodes, head, next, sizeOfCustomLinkedList);
        }

        private Node(Task task, Node<Task> prev, Node<Task> next) {
            this.task = task;
            this.prev = prev;
            this.next = next;
        }

        public String toString() {
            String result = "Post{task='" + task + "',tasksIdAndNodes.size()='" + tasksIdAndNodes.size() + "',prev=" + prev + ",next=" + next + '}';
            if (tasksIdAndNodes != null) {
            } else {
                result = result + ", tasksIdAndNodes=null";
            }
            return result;
        }

        private static final int SIZE_OF_HISTORY = 10;
        final private Map<Integer, Node<Task>> tasksIdAndNodes = new HashMap<>();
        private int sizeOfCustomLinkedList = 0;

        private ArrayList<Task> getTasks() {
            ArrayList<Task> tasks = new ArrayList<>();
            Node<Task> node = head;
            while (node.next != null) {
                tasks.add((Task) node);
            }
            return tasks;
        }

        private void linkLast(Task task) {
            final Node<Task> node = new Node<Task>(task, prev, null);
            prev = node;
        }

              public void add(Task task) {
            removeNode(task);
            getTasks();
        }

        private void removeNode(Task id) {
            final Node<Task> node = tasksIdAndNodes.remove(id);
            if (node.prev == null) {
                node.next.prev = null;
            } else if (node.prev != null && node != null) {
                node.next.prev = null;
            }
        }

        public void addLast(Task task) {
            final Node<Task> oldTail = prev;
            final Node<Task> newNode = new Node(task, prev, null);
            prev = newNode;
            if (oldTail == null) {
                head = newNode;
                sizeOfCustomLinkedList++;
            } else {
                oldTail.next = newNode;
                sizeOfCustomLinkedList++;
            }
        }
    }
}


