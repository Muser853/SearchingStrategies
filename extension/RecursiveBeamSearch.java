import java.util.Comparator;

public final class RecursiveBeamSearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;
    private final int beamWidth;

    public RecursiveBeamSearch(Boolean euclidean, boolean bidirectional, int beamWidth) {
        super(bidirectional);
        this.beamWidth = beamWidth;
        
        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
                euclidean, cell.g < 0 ? start : target))) : new Heap<>(Comparator.comparingInt(cell ->
                target.calculateHeuristics(euclidean, cell)));
    }

    public void reset() {
        for (Cell cell : heap) cell.reset();
        heap.clear();
    }
    public void addCell(Cell next) {
        // Only add cell if it's within beam width
        if (heap.size() < beamWidth) {
            heap.offer(next);
        }
        else{// Replace the worst cell if the new cell is better
            Cell worst = heap.peek();
            if (worst != null 
            && next.calculateHeuristics(bidirectional, target) 
            < worst.calculateHeuristics(bidirectional, target)){
                explored.addFirst(heap.poll());
                heap.offer(next);
            }
        }// Start recursive search if we're not at start/target
        if (next != start && next != target){
            if (next.prev == start){
                // Start forward search from current cell to target
                search(next, target);
            }
            else if (next.prev == target){
                // Start backward search from start to current cell
                search(start, next);
            }
        }
    }
    public void updateCell(Cell next) {
        heap.updatePriority(next);
    }
    public int numRemainingCells() {
        return heap.size();
    }
    public Cell findNextCell() {
        return heap.isEmpty() ? null : heap.poll();
    }
}