public final class IterativeDeepeningDFS extends AbstractSearch {
    private final LinkedList<Cell> stack;
    private int currentDepth = 1;
    public int gap;
    
    public IterativeDeepeningDFS(boolean bidirectional){
        super(bidirectional);
        this.stack = new LinkedList<>();
    }
    public void reset(){
        for(Cell cell : stack) cell.reset();
        stack.clear();
        currentDepth = 1;
    }
    protected void updateCell(Cell next){
    }
    protected void addCell(Cell next){
        if (Math.abs(next.g) == currentDepth){
            stack.addFirst(next);
        }
        else stack.addLast(next);
    }
    protected Cell findNextCell(){
        if (Math.abs(stack.getFirst().g) != currentDepth) currentDepth += gap;
        return stack.remove();
    }
    protected int numRemainingCells(){
        return stack.size();
    }
}