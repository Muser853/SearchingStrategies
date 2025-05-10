import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Iterator;
@SuppressWarnings("unchecked")
public class HashSet<T> implements Iterable<T> {

    private ArrayDeque<T>[] hashTable;
    private int size;
    //private final double loadFactor;

    public HashSet() {
        hashTable = (ArrayDeque<T>[]) new ArrayDeque[1<<22];
        size = 0;
    }
    public int size() {return size;}
    public void clear(){
        hashTable = (ArrayDeque<T>[]) new ArrayDeque[capacity()];
        size = 0;
    }
    private int capacity() {return hashTable.length;}
    private int hash(T item) {return Math.abs(item.hashCode() % capacity());}
    // private void resize(int newCapacity) {
    //     Iterator<T> iterator = iterator();
    //     hashTable = (ArrayDeque<T>[]) new ArrayDeque[newCapacity];
    //     while (iterator.hasNext()) // note: iterator.next() returns null only when iterator.hasNext() is false
    //         addToBucket(iterator.next());
    // }
    public boolean contains(T item) {return hashTable[hash(item)] != null && hashTable[hash(item)].contains(item);}

    public T put(T item) {
        int hashValue = hash(item);
        if (hashTable[hashValue]!= null){
            for (T t: hashTable[hashValue]){
                if (t.equals(item)) return t;
            }
        } else hashTable[hashValue] = new ArrayDeque<>();
        hashTable[hashValue].addFirst(item);
        return item;
    }

    public boolean add(T item) {
        if (contains(item)) return false;
        //if (++size > loadFactor * capacity()) resize((int) (capacity() / loadFactor));
        addToBucket(item);
        return true;
    }
    private void addToBucket(T item) {
        if (hashTable[hash(item)] == null) hashTable[hash(item)] = new ArrayDeque<>();
        hashTable[hash(item)].addFirst(item);
    }
    public boolean remove(T item) {return hashTable[hash(item)] != null && hashTable[hash(item)].remove(item);}

    public Iterator<T> iterator() {
        return new Iterator<>() {
            final ArrayDeque<T>[] iterArray = hashTable;
            int curIndex = getNext(-1);
            Iterator<T> curIterator = hasNext() ? iterArray[curIndex].iterator() : null;

            private int getNext(int index) {
                for (int i = index + 1; i < iterArray.length; i++) {
                    if (iterArray[i] != null && !iterArray[i].isEmpty())
                        return i;
                }
                return iterArray.length;
            }

            @Override
            public boolean hasNext() {
                return curIndex < iterArray.length;
            }

            @Override
            public T next() {
                if (curIterator == null) { // created on empty hashTable
                    return null; // no next element in hashTable
                }
                T next = curIterator.next();
                if (!curIterator.hasNext()) { // if nothing in this bucket
                    curIndex = getNext(curIndex); // ... find next non-empty bucket
                    if (curIndex < iterArray.length) {
                        curIterator = iterArray[curIndex].iterator();
                    }
                }return next;
            }
        };
    }
    public T[] toArray(){
        T[] arr = (T[]) new Object[size];
        for(int i = 0; i < size; i++) arr[i] = iterator().next();
        return arr;
    }
    public ArrayList<T> toArrayList() {
        ArrayList<T> al = new ArrayList<>();
        for (T t : this) al.add(t);
        return al;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HashTable capacity: ").append(hashTable.length).append("\n");
        for (int i = 0; i < hashTable.length; i++) {
            sb.append("index ").append(i).append(": ");
            if (hashTable[i] != null)
                sb.append(hashTable[i].toString());
            sb.append("\n");
        }
        return sb.delete(sb.length() - 1, sb.length()).toString(); // removes the last "\n" newline
    }
}