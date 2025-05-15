public final class RecursiveDepthLimitedDFS extends AbstractSearch{
    private final LinkedList<Cell> stack = new LinkedList<>();
    public int depthLimit;

    public RecursiveDepthLimitedDFS(boolean bidirectional){
        super(bidirectional);
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
            if (next != start && next != target){
                if (next.prev == start){
                    for (Cell nextNeighbors : next.neighbors){
                        if (nextNeighbors != next.prev && nextNeighbors.g == 0)
                            search(nextNeighbors, target);
                    }
                }else if (next.prev == target){
                    for (Cell nextNeighbors : next.neighbors){
                        if (nextNeighbors != next.prev && nextNeighbors.g == 0)
                            search(start, nextNeighbors);
                    }
                }
            }
        }
    }
    protected Cell findNextCell(){
        return stack.remove();
    }
}