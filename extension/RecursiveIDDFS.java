import java.util.ArrayDeque;

public final class RecursiveIDDFS extends AbstractMazeSearch {
    private int depthLimit; // Current depth limit for the depth-limited search

    public RecursiveIDDFS(boolean bidirectional){
        super(bidirectional);
        this.depthLimit = 0;
    }
    @Override
    void addCell(Cell next) {
        exploredCells.add(next);
        if (next.depth > depthLimit) {
            return;
        }
        next.prev = cur;
    }
    @Override
    void updateCell(Cell next) {
    }
    @Override
    int numRemainingCells() {
        return depthLimit;
    }

    @Override
    Cell findNextCell() {
        if (depthLimit == 0) return null;
        return start;
    }

    @Override
    boolean updatePath(Cell neighbor) {
        boolean improvedPath = false;
        
        // Check better path
        if (neighbor.prev != null && neighbor.prev != cur &&
            traceback(cur).size() + 1 < traceback(neighbor).size()) {
            neighbor.prev = cur;
            improvedPath = true;
        }
        
        // Check new path
        if (!improvedPath && neighbor.depth <= depthLimit) {
            neighbor.prev = cur;
            neighbor.updateDepth(cur);
            improvedPath = true;
        }
        
        return improvedPath;
    }

    @Override
    public ArrayDeque<Cell> search(Cell start, Cell target) {
        ArrayDeque<Cell> path;
        for(depthLimit = 0; depthLimit < 256; depthLimit++) {
            if ( ! (
                   path = super.search(start, target)
                   ).isEmpty()
            ) 
            return path;
        }
        return null;
    }
}