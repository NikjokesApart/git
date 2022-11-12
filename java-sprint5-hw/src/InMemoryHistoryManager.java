import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int SIZE_OF_HISTORY = 10;
    final private Map<Integer, Node<Task>> tasksIdAndNodes = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;
    private int sizeOfCustomLinkedList = 0;

    @Override
    public void add(Task task) {
        if (tasksIdAndNodes.containsKey(task.getTaskById())) {
            removeNode(tasksIdAndNodes.get(task.getTaskById()));
        }
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        Node<Task> task = tasksIdAndNodes.get(id);
        removeNode(task);
    }

    private static class Node<Task> {
        private Task data;
        private Node<Task> prev;
        private Node<Task> next;

        private Node(Node<Task> prev, Task data, Node<Task> next) {
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

    private ArrayList<Task> getTasks() {
        ArrayList<Task> historyTaskList = new ArrayList<>();
        Node<Task> node = head;
        historyTaskList.add(node.data);
        while (node.next != null) {
            historyTaskList.add(node.data);
            node = node.next;
        }
        historyTaskList.add(tail.data);
        return historyTaskList;
    }

    private void removeNode(Node<Task> nodeToBeRemoved) {
        if (sizeOfCustomLinkedList != 0) {
            if (nodeToBeRemoved.equals(head)) {
                final Node<Task> oldHead = head;
                if (head.equals(tail)) {
                    tail = null;
                } else {
                    head.next.prev = null;
                }
                head = oldHead.next;
                oldHead.next = null;
            }
        } else if (nodeToBeRemoved.equals(tail)) {
            tail = nodeToBeRemoved.prev;
            nodeToBeRemoved.prev.next = null;
        } else {
            nodeToBeRemoved.prev.next = nodeToBeRemoved.next;
            nodeToBeRemoved.next.prev = nodeToBeRemoved.prev;
        }
        --sizeOfCustomLinkedList;
        tasksIdAndNodes.remove(nodeToBeRemoved.data.getTaskById());
    }

    private void linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        ++sizeOfCustomLinkedList;
        tasksIdAndNodes.put(task.getTaskById(), tail);
    }
}


