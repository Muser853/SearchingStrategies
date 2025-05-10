import java.util.ArrayDeque;

final class RecursiveDFS extends AbstractMazeSearch {
    ArrayDeque<Cell> stack, path;

    RecursiveDFS(boolean bidirectional) {
        super(bidirectional);
        this.stack = new ArrayDeque<>();
    }
    @Override
    void addCell(Cell next) {
        exploredCells.add(next);
        stack.push(next);
    }
    @Override
    void updateCell(Cell next) {}
    @Override
    int numRemainingCells() {return stack.size();}
    @Override
    Cell findNextCell() {
        return stack.pop();
    }

    @Override
    boolean updatePath(Cell neighbor) {
        return false;
    }

    @Override
    public ArrayDeque<Cell> search(Cell start, Cell target) {
        reset();
        start.forwardVisited = true;
        this.start = start;

        target.forwardVisited = false;
        this.target = target;
        addCell(start);
        if (bidirectional) addCell(target);
        
        return dfsRecursive();
    }
    ArrayDeque<Cell> dfsRecursive() {
        cur = findNextCell();

        for (Cell neighbor : cur.neighbors){
            if (neighbor.forwardVisited == null){
                neighbor.forwardVisited = cur.forwardVisited;
                neighbor.prev = cur;
                addCell(neighbor);
            }
            else if (neighbor.forwardVisited != cur.forwardVisited){
                return traceback(neighbor);
            }
            else if (traceback(cur).size() + 1 < traceback(neighbor).size()){
                neighbor.prev = cur;
            }
        }return null;
    }
}