import java.util.ArrayDeque;
import java.util.Queue;

public class RecursiveBFS extends AbstractMazeSearch {
    private final Queue<Cell> queue;
    
    public RecursiveBFS(boolean bidirectional) {
        super(bidirectional);
        this.queue = new ArrayDeque<>();
    }
    
    @Override
    public void addCell(Cell next) {
    }
    
    @Override
    public void updateCell(Cell next) {
    }
    
    @Override
    public boolean updatePath(Cell neighbor) {
        return false;
    }
    
    @Override
    public int numRemainingCells() {
        return queue.size();
    }
    
    @Override
    public Cell findNextCell() {
        return null;
    }
    
    private boolean recursiveBFS() {
        if (queue.isEmpty()) return false;
        cur = queue.poll();
    
        for (Cell neighbor : cur.neighbors) {
            if (neighbor.forwardVisited == null) {
                neighbor.forwardVisited = cur.forwardVisited;
                neighbor.prev = cur;
                queue.offer(neighbor);
                exploredCells.add(neighbor);
            }
            else if (neighbor.forwardVisited != cur.forwardVisited){
                return true;
            }
            else if (traceback(neighbor).size()
                     > traceback(cur).size() + 1) {
                neighbor.prev = cur;
            }
        }
        return recursiveBFS();
    }
    @Override
    public ArrayDeque<Cell> search(Cell start, Cell target) {
        reset();
        
        this.queue.clear();
        
        start.forwardVisited = true;
        target.forwardVisited = false;
        exploredCells.add(start);
        this.queue.offer(start);
        
        if (bidirectional) this.queue.offer(target);
        
        this.start = start;
        this.target = target;
        return recursiveBFS() ? traceback(cur)
            : null;
    }
}
