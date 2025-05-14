import java.util.Comparator;

public class BeamSearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;
    private final int beamWidth;

    public BeamSearch(Boolean euclidean, boolean bidirectional, int beamWidth) {
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
        if (heap.size() < beamWidth){
            heap.offer(next);
        }
        else{// Replace the worst cell if the new cell is better
            Cell worst = heap.peek();
            if (worst != null 
            && next.calculateHeuristics(bidirectional, target) 
            < worst.calculateHeuristics(bidirectional, target)) {
                explored.addFirst(heap.poll());
                heap.offer(next);
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