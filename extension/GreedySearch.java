import java.util.Comparator;

public final class GreedySearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;

    public GreedySearch(Boolean euclidean, boolean bidirectional) {
        super(bidirectional);
        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
                euclidean, cell.g < 0 ? target : start))) : new Heap<>(Comparator.comparingInt(cell ->
                target.calculateHeuristics(euclidean, cell)));
    }
    void reset(){
        for(Cell cell: heap) cell.reset();
        heap.clear();
    }
    @Override
    void addCell(Cell next){
        heap.offer(next);
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
        return heap.isEmpty() ? null: heap.poll();
    }
}