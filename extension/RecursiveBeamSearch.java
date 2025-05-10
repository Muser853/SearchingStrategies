import java.util.Comparator;

public final class RecursiveBeamSearch extends AbstractMazeSearch{
    private final PriorityQueue<Cell> heap;

    public RecursiveBeamSearch(Boolean euclidean, boolean bidirectional) {
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> 
            target.calculateHeuristics(euclidean, cell)
        ));
    }
    @Override
    void reset() {
        super.reset();
        heap.clear();
    }
    @Override
    void addCell(Cell next) {
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
        return heap.isEmpty() ? null : heap.poll();
    }
    @Override
    boolean updatePath(Cell neighbor) {
        return false;
    }
}
