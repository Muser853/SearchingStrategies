import java.util.ArrayDeque;
import java.util.Queue;

public final class MazeBreadthFirstSearch extends AbstractMazeSearch {
    private final Queue<Cell> queue;

    public MazeBreadthFirstSearch(boolean bidirectional){
        super(bidirectional);
        this.queue = new ArrayDeque<>();
    }
    @Override
    void addCell(Cell next) {
        exploredCells.add(next);
        queue.offer(next);
    }
    @Override
    void updateCell(Cell next) {}

    @Override
    int numRemainingCells() {
        return queue.size();
    }

    @Override
    Cell findNextCell() {
        if (queue.isEmpty()) return null;
        return queue.poll();
    }

    @Override
    boolean updatePath(Cell neighbor) {
        return false;
    }
}