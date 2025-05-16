public final class IterativeDeepeningDFS extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();
    private int currentDepth = 1;
    public int gap;
    
    public IterativeDeepeningDFS(boolean bidirectional){
        super(bidirectional);
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
    protected void addCell(Cell next){
        if (Math.abs(next.g) == currentDepth){
            stack.addFirst(next);
        }
        else stack.addLast(next);
    }
    protected Cell findNextCell(){
        if (Math.abs(stack.getFirst().g) > currentDepth) currentDepth += gap;
        return stack.remove();
    }
    protected int numRemainingCells(){
        return stack.size();
    }
}