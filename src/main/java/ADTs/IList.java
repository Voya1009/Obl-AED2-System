package ADTs;

import java.util.Iterator;

public interface IList<T> {
    void add(T data);
    void delete(T data);
    int length();
    boolean exist(T data);
    T recover(T data);
    boolean isEmpty();
    boolean isFull();
    void print();
    Iterator<T> iterator();
}
