interface PriorityQueue<T> {
    T peek();
    T poll();
    void offer(T item);
    void updatePriority(T item);
    void clear();
    int size();
    boolean isEmpty();
}