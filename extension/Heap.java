import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;

public class Heap<T> implements PriorityQueue<T>{
    private int size;
    private final boolean maxHeap;
    public final Comparator<T> comparator;
    public final ArrayList<T> heap;
    protected final HashMap<T, Integer> indexMap;

    public Heap(Comparator<T> comparator, boolean maxHeap){
        this.comparator = comparator;
        this.maxHeap = maxHeap;
        this.heap = new ArrayList<>(1<<22);
        this.indexMap = new HashMap<>();
        this.size = 0;
    }
    public Comparator<T> comparator(){return comparator;}
    public Iterator<T> iterator(){
        return heap.iterator();
    }
    public Heap(Comparator<T> comparator){this(comparator, false);}
    public int compareTo(T a, T b){
        return maxHeap ? comparator.compare(b, a) : comparator.compare(a, b);
    }
    private void swap(int idx1, int idx2) {Collections.swap(heap, idx1, idx2);}
    private int getParentIdx(int idx) {return (idx - 1) / 2;}
    private int getLeftChildIdx(int idx){return 2 * idx + 1;}
    private int getRightChildIdx(int idx) {return 2 * idx + 2;}

    private void bubbleUp(int idx){
        int parent;
        while (idx > 0){
            parent = getParentIdx(idx);

            if (compareTo(heap.get(idx),
             heap.get(parent)) > 0) break;
            swap(idx, parent);
            idx = parent;
        }
    }
    public void offer(T item){
        heap.add(item);
        indexMap.put(item, size);
        bubbleUp(size);
        size++;
    }
    private void bubbleDown(int idx) {
        int child;
        while (size > getLeftChildIdx(idx)){
            child = (getRightChildIdx(idx)== size|| compareTo(
                    heap.get(getLeftChildIdx(idx)), 
                    heap.get(getRightChildIdx(idx))) < 0)
                    ? getLeftChildIdx(idx) : getRightChildIdx(idx);

            if (compareTo(heap.get(idx),
             heap.get(child)) < 0) break;
            swap(idx, child);
            idx = child;
        }
    }
    public int size() {return size;}
    public T peek() {return heap.getFirst();}
    public T getLast(){return heap.getLast();}
    
    public T poll(){
        T root = heap.getFirst();
        swap(0, size);
        indexMap.remove(heap.remove(size));
        if (size > 0) bubbleDown(0);
        return root;
    }
    public boolean isEmpty() {return size == 0;}
    
    public void updatePriority(T item){
        bubbleUp(indexMap.get(item));
        bubbleDown(indexMap.get(item));
    }
    public void clear() {
        heap.clear();
        indexMap.clear();
        size = 0;
    }
    public String toString() {
        return toString(0, 0);
    }
    private String toString(int idx , int depth){
        if (idx >= this.size()) return "";
        String left = toString(getLeftChildIdx(idx), depth + 1);
        String right = toString(getRightChildIdx(idx), depth + 1);
        String myself = "\t".repeat(depth) + this.heap.get(idx) + "\n";
        return right + myself + left;
    }
}