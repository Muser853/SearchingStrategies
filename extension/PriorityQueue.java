import java.lang.Iterable;
import java.util.Comparator;

public interface PriorityQueue<T> extends Iterable<T>{
    Comparator<T> comparator();
    T getLast();
    T peek();
    T poll();
    void offer(T item);
    void updatePriority(T item);
    void clear();
    int size();
    boolean isEmpty();
}