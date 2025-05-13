import java.util.Comparator;

public final class RecursiveASearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;

    public RecursiveASearch(Boolean euclidean, boolean bidirectional) {
        super(bidirectional);

        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell -> Math.abs(cell.g) + cell.calculateHeuristics(
                euclidean, cell.g < 0 ? target : start))) : new Heap<>(Comparator.comparingInt(cell ->
                Math.abs(cell.g) + target.calculateHeuristics(euclidean, cell)));
    }
    @Override
    void reset(){
        for (Cell cell: heap) cell.reset();
        heap.clear();
    }
    @Override
    void addCell(Cell next) {
        heap.offer(next);

        if (next != start && next != target){
            if (next.prev == start) search(next, target);
            else if (next.prev == target) search(start, next);
        }
    }
    @Override
    void updateCell(Cell next){
        heap.updatePriority(next);
    }
    @Override
    int numRemainingCells(){
        return heap.size();
    }
    @Override
    Cell findNextCell(){
        return heap.isEmpty() ? null : heap.poll();
    }
}