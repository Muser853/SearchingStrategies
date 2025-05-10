import java.util.Comparator;

public final class GreedySearch extends AbstractMazeSearch {
    private final PriorityQueue<Cell> heap;

    public GreedySearch(Boolean euclidean, boolean bidirectional) {
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> 
            target.calculateHeuristics(euclidean, cell)
        ));
    }
    @Override
    void addCell(Cell next){
        exploredCells.add(next);
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
    Cell findNextCell() {
        return heap.isEmpty() ? null: heap.poll();
    }
    @Override
    boolean updatePath(Cell neighbor) {
        return false;
    }
}
