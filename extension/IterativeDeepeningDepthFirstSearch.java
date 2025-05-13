public final class IterativeDeepeningDepthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> stack;
    
    public IterativeDeepeningDepthFirstSearch(boolean bidirectional){
        super(bidirectional);
        this.stack = new LinkedList<>();
    }
    void reset(){
        for(Cell cell : stack) cell.reset();
        stack.clear();
    }
    void updateCell(Cell next){
    }
    void addCell(Cell next){
        stack.addFirst(next);
    }
    Cell findNextCell(){
        return stack.isEmpty() ? null : stack.remove();
    }
    int numRemainingCells(){
        return stack.size();
    }
    boolean depthLimitedSearch(Cell current, int depthLimit) {
        stack.clear();
        stack.addFirst(current);
        
        while (!stack.isEmpty()){
            cur = stack.remove();
            
            if (cur.g < depthLimit){
                for (Cell neighbor : cur.neighbors){
                    if (neighbor.prev == null){
                        neighbor.prev = cur;
                        neighbor.g = cur.g + 1;
                        stack.addFirst(neighbor);
                        explored.addFirst(neighbor);
                    }
                    else if (Integer.signum(neighbor.g) != Integer.signum(cur.g)){
                        return true;
                    }
                    else if (cur.g + 1 < neighbor.g) {
                        neighbor.prev = cur;
                        updateCell(neighbor);
                    }
                }
            }
        }return false;
    }
}