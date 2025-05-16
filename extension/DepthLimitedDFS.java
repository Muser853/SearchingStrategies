public final class DepthLimitedDFS extends AbstractSearch{
    private final LinkedList<Cell> stack = new LinkedList<>();
    public int depthLimit;

    public DepthLimitedDFS(boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
    }
    public void reset(){
        for(Cell cell : stack) cell.reset();
        stack.clear();
    }
    protected int numRemainingCells(){
        return stack.size();
    }
    protected void updateCell(Cell next){
    }
    protected boolean addCell(Cell next){
        if (Math.abs(next.g) > depthLimit){
            explored.addFirst(next);
            return false;
        }
        stack.addFirst(next);
        return true;
    }
    protected Cell findNextCell(){
        return stack.remove();
    }
}