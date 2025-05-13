import java.util.Comparator;

public final class RecursiveBeamSearch extends AbstractSearch{
    private final PriorityQueue<Cell> heap;

    public RecursiveBeamSearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
                euclidean, cell.g < 0 ? start : target))) : new Heap<>(Comparator.comparingInt(cell ->
                target.calculateHeuristics(euclidean, cell)));
    }
    void reset(){
        for(Cell cell : heap) cell.reset();
        heap.clear();
    }
    void addCell(Cell next){
        heap.offer(next);

        if (next != start && next != target){
            if (next.prev == start) search(next, target);
            else if (next.prev == target) search(start, next);
        }
    }
    @Override
    void updateCell(Cell next) {
        heap.updatePriority(next);
    }
    @Override
    int numRemainingCells() {
        return heap.size();
    }
    @Override
    Cell findNextCell(){
        return heap.isEmpty() ? null : heap.poll();
    }
}