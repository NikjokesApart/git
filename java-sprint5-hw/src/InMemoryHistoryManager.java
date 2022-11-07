import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {


    @Override
    public void add(Task task) {

    }

    @Override
    public List<Task> getHistory() {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    //public List<Task> historyTaskList = new LinkedList<>();
    private static class Node {
        Task task;
        Node prev;
        Node next;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return sizeOfCustomLinkedList == node.sizeOfCustomLinkedList && Objects.equals(task, node.task) && Objects.equals(prev, node.prev) && Objects.equals(next, node.next) && Objects.equals(tasksIdAndNodes, node.tasksIdAndNodes) && Objects.equals(head, node.head) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(task, prev, next, tasksIdAndNodes, head, next, sizeOfCustomLinkedList);
        }

        private Node(Task task, Node prev, Node next) {
            this.task = task;
            this.prev = prev;
            this.next = next;
        }

        public String toString() {
            //TODO xxxxx
            return null;
        }


        private static final int SIZE_OF_HISTORY = 10;
        final private Map<Integer, Node<Task>> tasksIdAndNodes = new HashMap<>();

        private Node<Task> head;
        private Node<Task> next;
        private int sizeOfCustomLinkedList = 0;


        private ArrayList<Task> getTasks() {
            ArrayList<Task> tasks = new ArrayList<>();
            Node node = head;
            while (node.next != null) {
                tasks.add(node);
            }
            return tasks;
        }

        private void linkLast(Task task) {
            final Node node = new Node(task, last, null);

            //TODO
            last = node;
        }

        @Override
        public List<Task> getHistory() {
            return getTasks();
        }

        @Override
        public void add(Task task) {
            removeNode(task);
            getTasks();
            //add as a last one
            //add info the map
        }

        private void removeNode(Task id) {
            final Node node = tasksIdAndNodes.remove(id);
            if (node.prev == null) {
                first = node.next;
                node.next.prev = null;
            } else if (node.prev != null && node != null) {
                first = node.next;
                node.next.prev = null;
            } else {

            }

            public void addLast (Task element){
                final Node<Task> oldTail = (Node<Task>) tail;
                final Node<Task> newNode = new Node<Task>(tail, element, null);
                tail = newNode;
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
}


