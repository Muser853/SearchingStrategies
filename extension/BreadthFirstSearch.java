import java.util.ArrayDeque;
import java.util.Queue;

public final class BreadthFirstSearch extends AbstractSearch {
    private final Queue<Cell> queue;

    public BreadthFirstSearch(boolean bidirectional){
        super(bidirectional);
        this.queue = new ArrayDeque<>();
    }
    @Override
    void reset(){
        queue.clear();
    }
    @Override
    void addCell(Cell next) {
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
}