public final class BreadthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> queue = new LinkedList<>();
    
    public BreadthFirstSearch(boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
    }
    public void reset(){
        for (Cell cell : queue) cell.reset();
        queue.clear();
    }
    protected boolean addCell(Cell next) {
        queue.addLast(next);
        return true;
    }
    protected void updateCell(Cell next){}

    public int numRemainingCells(){
        return queue.size();
    }

    protected Cell findNextCell(){
        return queue.remove();
    }
}