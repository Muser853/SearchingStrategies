public final class IterativeDeepeningDepthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> stack;
    private int currentDepth;
    
    public IterativeDeepeningDepthFirstSearch(boolean bidirectional){
        super(bidirectional);
        this.stack = new LinkedList<>();
    }
    void reset(){
        for(Cell cell : stack) cell.reset();
        stack.clear();
        currentDepth = 0;
    }
    void updateCell(Cell next){
        // Update the cell with depth information
        next.depth = currentDepth + 1;
        stack.addFirst(next);
    }
    void addCell(Cell next){
        // Initialize depth for new cells
        next.depth = currentDepth + 1;
        stack.addFirst(next);
    }
    
    Cell findNextCell(){
        if (stack.isEmpty()) {
            // If stack is empty, restart search from root
            currentDepth = 0;
            return null;
        }
        cur = stack.remove();
        currentDepth = cur.depth;
        return cur;
    }
    
    int numRemainingCells(){
        return stack.size();
    }
}