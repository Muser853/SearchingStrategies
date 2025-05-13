import java.util.ArrayDeque;

public final class DepthFirstSearch extends AbstractSearch {
    private final ArrayDeque<Cell> stack;

    public DepthFirstSearch(boolean bidirectional) {
        super(bidirectional);
        this.stack = new ArrayDeque<>();
    }
    void reset(){
        for (Cell cell : stack) cell.reset();
        stack.clear();
    }
    @Override
    void addCell(Cell next) {
        stack.push(next);
    }
    @Override
    void updateCell(Cell next) {}
    @Override
    int numRemainingCells() {return stack.size();}
    @Override
    Cell findNextCell() {return stack.isEmpty() ? null : stack.pop();}
}