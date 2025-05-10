import java.util.Comparator;

public class BeamSearch extends AbstractMazeSearch{
    private final PriorityQueue<Cell> heap;

    public BeamSearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell ->
            cell.calculateHeuristics(
                euclidean, (cell.forwardVisited
                ? target : start))
                : new Heap<>(Comparator.comparingInt(cell ->
                target.calculateHeuristics(
                        euclidean, cell))
        ))), true);
    }
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
