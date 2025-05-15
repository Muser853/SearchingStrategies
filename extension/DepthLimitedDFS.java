public final class DepthLimitedDFS extends AbstractSearch{
    private final LinkedList<Cell> stack;
    public int depthLimit;

    public DepthLimitedDFS(boolean bidirectional){
        super(bidirectional);
        this.stack = new LinkedList<>();
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
    protected void addCell(Cell next){
        if (Math.abs(next.g) > depthLimit){
            explored.addFirst(next);
        }
        else{
            stack.addFirst(next);
        }
    }
    protected Cell findNextCell(){
        return stack.remove();
    }
}