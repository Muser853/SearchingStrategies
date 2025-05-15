import java.util.Iterator;

public final class LinkedList<T> implements Iterable<T> {
    private int size;
    protected Node<T> head, tail;
    
    static class Node<T>{
        protected T data;
        protected Node<T> next;

        public Node(T data, Node<T> next){
            this.data = data;
            this.next = next;
        }
    }
    public LinkedList(){
        this.size = 0;
        this.head = this.tail = null;
    }
    public LinkedList(LinkedList<T> list){
        this();
        for (T item : list){
            this.addFirst(item);
        }
    }
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    
    @Override
    public Iterator<T> iterator(){
        return new LLIterator();
    }
    
    private class LLIterator implements Iterator<T> {
        protected Node<T> current = head;
        
        @Override
        public boolean hasNext(){
            return current != null;
        }
        @Override
        public T next(){
            T data = current.data;
            current = current.next;
            return data;
        }
    }
    public void addLast(T item){
        if (item == null) return;
        if (size == 0){
            head = tail = new Node<>(item, null);
        } else {
            tail = tail.next = new Node<>(item, null);
        }
        size++;
    }
    public void addFirst(T item){
        if (item == null) return;
        head = new Node<>(item, head);
        if (size == 0) tail = head;
        size++;
    }
    public T getFirst(){
        return head.data;
    }
    public T getLast(){
        return tail.data;
    }
    public T remove(){
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    public void clear(){
        head = tail = null;
        size = 0;
    }
}