public final class DepthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();

    public DepthFirstSearch(boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
    }
    public void reset(){
        for (Cell cell : stack) cell.reset();
        stack.clear();
    }
    protected boolean addCell(Cell next){
        stack.addFirst(next);
        return true;
    }
    void updateCell(Cell next) {
    }
    int numRemainingCells() {return stack.size();}
    Cell findNextCell() {
        return stack.isEmpty() ? null : stack.remove();
    }
}