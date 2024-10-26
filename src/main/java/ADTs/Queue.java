package ADTs;

public class Queue<T> implements IQueue<T> {
    private Node<T> head;
    private Node<T> last;
    private int length;

    public void enqueue(T data) {
        if (this.head == null) {
            head = new Node<>(data);
            last = head;
        } else {
            last.next = new Node<>(data);
            last = last.next;
        }
        this.length++;
    }

    public T unqueue() {
        T data = this.head.data;
        head = head.next;
        this.length--;
        if (this.head == null) {
            last = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    private class Node<Q> {
        private final Q data;
        private Node<Q> next;

        public Node(Q data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
