import java.util.Objects;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private int size;
    private Node<T> head, tail;
    
    @Override
    public Iterator<T> iterator() {return new LLIterator(this.head);}
    private class LLIterator implements Iterator<T> {
        public LLIterator(Node<T> head) {this.next = head;}
        private Node<T> current = null;
        private Node<T> next = null;
        @Override
        public boolean hasNext() {return next != null;}
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = next;
            next = next.next;
            return current.getData();
        }
    }
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
        public T getData() {return data;}
    }
    public LinkedList() {
        this.size = 0;
        head = null;
    }

    public int size() {return size;}

    public boolean isEmpty() {return size == 0;}
    public void clear() {
        head = null;
        size = 0;
    }
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T item = head.getData();
        head = head.next;
        size--;
        return item;
    }
    public T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        
        T item;
        if (index == 0) {
            item = head.getData();
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++)
                current = current.next;
            
            item = current.next.getData();
            current.next = current.next.next;
        }
        size--;
        return item;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedList)) {
            return false;
        }
        LinkedList<?> other = (LinkedList<?>) o;
        if (size != other.size) {
            return false;
        }
        Node<T> current = head;
        Node<?> otherCurrent = other.head;
        while (current != null) {
            if (!Objects.equals(current.getData(), otherCurrent.getData())) {
                return false;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
        return true;
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = new Node<T>(item, head);
        } else {
            ;
        }
        size++;
    }

    public boolean contains(T item) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(item, current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.getData();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    public void addLast(T item) {
        if (isEmpty())
            head = tail = new Node<T>(item, null);
        else {
            tail = tail.next = new Node<T>(item, null);
        }
        size++;
    }
    public void addFirst(T item) {
        head = new Node<>(item, head);
        size++;
    }
    public T getFirst(){
        return head.getData();
    }
    public T getLast(){
        return tail.getData();
    }
}