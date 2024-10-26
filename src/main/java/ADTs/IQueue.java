package ADTs;

public interface IQueue<T> {
    void enqueue(T data);
    T unqueue();
    boolean isEmpty();
}
