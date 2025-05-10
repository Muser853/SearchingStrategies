import java.util.ArrayDeque;

public final class MazeDepthFirstSearch extends AbstractMazeSearch {
    private final ArrayDeque<Cell> stack;

    public MazeDepthFirstSearch(boolean bidirectional) {
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
    Cell findNextCell() {return stack.isEmpty() ? null : stack.pop();}

    @Override
    boolean updatePath(Cell neighbor) {
        return false;
    }
}