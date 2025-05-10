import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Heap<T> implements PriorityQueue<T> {
    private int size;
    private final boolean maxHeap;
    public final Comparator<T> comparator;
    public final ArrayList<T> heap;
    private final Map<T, Integer> indexMap;

    public Heap(Comparator<T> comparator, boolean maxHeap) {
        this.comparator = comparator;
        this.maxHeap = maxHeap;
        this.heap = new ArrayList<>(1<<22);
        this.indexMap = new HashMap<>();
        this.size = 0;
    }
    public Heap(Comparator<T> comparator){this(comparator, false);}
    public int compareTo(T a, T b) {return maxHeap ? comparator.compare(b, a) : comparator.compare(a, b);}
    private void swap(int idx1, int idx2) {Collections.swap(heap, idx1, idx2);}
    private int getParentIdx(int idx) {return (idx - 1) / 2;}
    private int getLeftChildIdx(int idx){return 2 * idx + 1;}
    private int getRightChildIdx(int idx) {return 2 * idx + 2;}
    private void bubbleUp(int idx) {
        int parent;
        while (idx != 0) {
            parent = getParentIdx(idx);
            if (compareTo(heap.get(idx), heap.get(parent)) > 0) break;
            swap(idx, parent);
            idx = parent;
        }
    }
    private void bubbleDown(int idx) {
        int swapIdx;
        while (getLeftChildIdx(idx) < size) {
            swapIdx = (getRightChildIdx(idx)== size||
                    compareTo(heap.get(getLeftChildIdx(idx)),
                    heap.get(getRightChildIdx(idx)))
                    <0
            ) ? getLeftChildIdx(idx):getRightChildIdx(idx);

            if (compareTo(
                heap.get(swapIdx),
                heap.get(idx))>0
            ) break;
            swap(idx, swapIdx);
            idx = swapIdx;
        }
    }
    public String toString() {
        return toString( 0 , 0 );
    }
    private String toString( int idx , int depth ) {
        if (idx >= this.size() ) return "";
        String left = toString(getLeftChildIdx( idx ) , depth + 1 );
        String right = toString(getRightChildIdx( idx ) , depth + 1 );
        String myself = "\t".repeat(depth) + this.heap.get( idx ) + "\n";
        return right + myself + left;
    }
    @Override
    public void offer(T item) {
        if (item == null) throw new NullPointerException();
        if (indexMap.containsKey(item)) return; // Already in heap
        heap.add(item);
        indexMap.put(item, size);
        ++size;
        bubbleUp(size-1);
    }
    @Override
    public int size() {return size;}
    @Override
    public T peek() {return size == 0 ? null : heap.getFirst();}
    @Override
    public T poll() {
        if (--size == -1) return null;
        T root = heap.getFirst();
        swap(0, size);
        T removed = heap.remove(size);
        indexMap.remove(removed);
        if (size != 0) bubbleDown(0);
        return root;
    }
    @Override
    public boolean isEmpty() {return size == 0;}
    @Override
    public void updatePriority(T item) {
        Integer index = indexMap.get(item);
        if (index != null) {
            bubbleUp(index);
            bubbleDown(index);
        }
    }
    @Override
    public boolean contains(T item) {
        return indexMap.containsKey(item);
    }
    @Override
    public void clear() {
        heap.clear();
        indexMap.clear();
        size = 0;
    }
}
