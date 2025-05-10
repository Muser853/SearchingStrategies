import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMazeSearch {
    public Cell start, target, cur;
    public final List<Cell> exploredCells;
    protected ArrayDeque<Cell> path;
    protected final boolean bidirectional;

    public AbstractMazeSearch(boolean bidirectional) {
        this.bidirectional = bidirectional;
        this.start = null;
        this.target = null;
        this.cur = null;
        this.path = new ArrayDeque<>();
        this.exploredCells = new ArrayList<>();
    }
    abstract void addCell(Cell next);
    abstract void updateCell(Cell next);
    abstract int numRemainingCells();
    abstract Cell findNextCell();
    abstract boolean updatePath(Cell neighbor);//for shorter path

    int getCellsExplored() {return exploredCells.size();}

    ArrayDeque<Cell> traceback(Cell cell){
        path.clear();
        path.addFirst(cell);

        while(path.getFirst().prev != null){
            path.addFirst(
                         path.getFirst().prev);
        }
        if (cur.forwardVisited == cell.forwardVisited){
            return path;
        }
        path.addLast(cur);
        
        while (path.getLast().prev != null){
            path.addLast(
                path.getLast().prev);
        }
        return path;
    }
   
    protected final ArrayDeque<Cell> search(Cell start, Cell target) {
        if(start == target) {
            System.out.println("start == target");
            return null;
        }
        start.forwardVisited = true;
        target.forwardVisited = false;
        this.start = start;
        this.target = target;
        
        addCell(start);
        if(bidirectional) addCell(target);

        while (numRemainingCells() > 0) {
            if((cur = findNextCell()) == null) break;

            for (Cell neighbor : cur.neighbors) {
                if (neighbor.forwardVisited == null){
                    neighbor.forwardVisited = cur.forwardVisited;
                    neighbor.prev = cur;
                    addCell(neighbor);
                }
                else if (neighbor.forwardVisited != cur.forwardVisited){
                    path = traceback(neighbor);
                    for (Cell cell : exploredCells) cell.reset();
                    target.reset();
                    
                    exploredCells.clear();
                    return path;
                }
                else if (updatePath(neighbor)) {
                    updateCell(neighbor);
                }
                else if (cur.g +1 < neighbor.g) {
                    neighbor.prev = cur;
                    updateCell(neighbor);
                }
            }
        }return null;
    }
}