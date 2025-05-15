public final class DepthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> stack;

    public DepthFirstSearch(boolean bidirectional) {
        super(bidirectional);
        this.stack = new LinkedList<>();
    }
    void reset(){
        for (Cell cell : stack) cell.reset();
        stack.clear();
    }
    void addCell(Cell next) {
        stack.addFirst(next);
    }
    void updateCell(Cell next) {
    }
    int numRemainingCells() {return stack.size();}
    Cell findNextCell() {
        return stack.isEmpty() ? null : stack.remove();
    }
}