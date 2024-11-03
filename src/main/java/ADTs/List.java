package ADTs;

import java.util.Iterator;

public class List<T> implements IList<T> {

    private Node<T> head;
    protected int length;

    public List() {
        this.head = null;
        this.length = 0;
    }

    public void add(T data) {
        head = new Node<T>(data, head);
        length++;
    }

    public void delete(T data) {}

    public int length() {
        return length;
    }

    public boolean exist(T data) {
        Node<T> aux = head;
        while (aux != null) {
            if (aux.getData().equals(data)) return true;
            aux = aux.getNext();
        }
        return false;
    }

    public T recover(T dato) {
        Node<T> aux = head;
        while (aux != null) {
            if (aux.getData().equals(dato)) return aux.getData();
            aux = aux.getNext();
        }
        return null;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return false;
    }

    public void print() {
        Node<T> aux = head;
        while (aux != null) {
            if (aux.getNext() != null) System.out.print(aux.getData() + " -> ");
            else System.out.print(aux.getData());
            aux = aux.getNext();
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private  Node<T> aux = head;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T data = aux.data;
                aux = aux.next;
                return data;
            }
        };
    }

    private static class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T dato, Node<T> sig) {
            this.data = dato;
            this.next = sig;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}
