import java.util.Comparator;

public final class RecursiveASearch extends AbstractMazeSearch {
    private final PriorityQueue<Cell> heap;

    public RecursiveASearch(Boolean euclidean, boolean bidirectional) {
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell ->target.calculateHeuristics(euclidean, cell)
        + (cur.prev == null ? 0 : cur.prev.g + 1)
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
        if ((cur.prev == null ? 1 : cur.prev.g + 2) < neighbor.g) {
            neighbor.prev = cur;
            neighbor.g = cur.g + 1;
            return true;
        }
        return false;
    }
}