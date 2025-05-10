import java.util.ArrayDeque;

public final class IterativeDeepeningDepthFirstSearch extends IterativeDeepeningSearch {
    private final ArrayDeque<Cell> stack;
    
    public IterativeDeepeningDepthFirstSearch(boolean bidirectional){
        super(bidirectional);
        this.stack = new ArrayDeque<>();
    }
    boolean depthLimitedSearch(Cell current, int depthLimit) {
        stack.clear();
        stack.push(current);
        exploredCells.add(current);
        
        while (!stack.isEmpty()) {
            cur = stack.pop();
            
            if (cur.g < depthLimit) {
                for (Cell neighbor : cur.neighbors) {
                    if (neighbor.forwardVisited == null){
                        neighbor.forwardVisited = cur.forwardVisited;
                        neighbor.prev = cur;
                        neighbor.g = cur.g + 1;
                        stack.push(neighbor);
                        exploredCells.add(neighbor);
                    }
                    else if (neighbor.forwardVisited != cur.forwardVisited){
                        return true;
                    }
                    else if (cur.g + 1 < neighbor.g) {
                        neighbor.prev = cur;
                        updateCell(neighbor);
                    }
                }
            }
        }return false;
    }
    @Override
    public ArrayDeque<Cell> search(Cell start, Cell target) {
        start.reset();
        this.start = start;
        this.target = target;
        
        for (int depthLimit = 0; depthLimit < 256; depthLimit++) {
            if (depthLimitedSearch(start, depthLimit)) {
                return traceback(target);
            }
        }return null;
    }
}