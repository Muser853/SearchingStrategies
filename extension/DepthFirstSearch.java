public final class DepthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();

    public DepthFirstSearch(boolean bidirectional) {
        super(bidirectional);
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