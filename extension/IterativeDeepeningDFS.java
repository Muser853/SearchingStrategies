public final class IterativeDeepeningDFS extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();
    private int currentDepth = 1;
    public int gap;
    
    public IterativeDeepeningDFS(boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
    }
    public void reset(){
        currentDepth = 1;
        for(Cell cell : stack) cell.reset();
        stack.clear();
    }
    protected void updateCell(Cell next){
        if (Math.abs(next.g) <= currentDepth){
            stack.addFirst(stack.remove(next));
        }
    }
    protected boolean addCell(Cell next){
        if (Math.abs(next.g) > currentDepth){
            stack.addLast(next);
            return false;
        }
        stack.addFirst(next);
        return true;
    }
    protected Cell findNextCell(){
        if (Math.abs(stack.getFirst().g) > currentDepth) currentDepth += gap;
        
        return stack.remove();
    }
    protected int numRemainingCells(){
        return stack.size();
    }
}