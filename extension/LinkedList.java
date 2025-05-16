import java.util.Iterator;

public final class LinkedList<T> implements Iterable<T>{
    private int size;
    Node<T> head, tail;
    
    static class Node<T>{
        T data;
        Node<T> next;

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
            addFirst(item);
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
        if (size == 0) head = tail = new Node<>(item, null);
        else tail = tail.next = new Node<>(item, null);
        size++;
    }
    public void addFirst(T item){
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
    public T remove(T item){
        Node<T> current = head;
        Node<T> prev = null;
        while (current != null && !current.data.equals(item)){
            prev = current;
            current = current.next;
        }
        if (current == null) return null;
        if (prev == null) head = current.next;
        else prev.next = current.next;
        System.out.println(--size);
        return current.data;
    }
    public void clear(){
        head = tail = null;
        size = 0;
    }
}