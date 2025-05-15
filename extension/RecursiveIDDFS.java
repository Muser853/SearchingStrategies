public final class RecursiveIDDFS extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();
    private int currentDepth = 1;
    public int gap;

    public RecursiveIDDFS(boolean bidirectional){
        super(bidirectional);
    }
    public void reset(){
        currentDepth = 1;
        for (Cell cell: stack) cell.reset();
        stack.clear();
    }
    protected void addCell(Cell next){
        if (Math.abs(next.g) == currentDepth){
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
        }else stack.addLast(next);
    }
    protected void updateCell(Cell next){
    }
    public int numRemainingCells(){
        return stack.size();
    }
    protected Cell findNextCell(){
        if (Math.abs(stack.getFirst().g) > currentDepth) currentDepth += gap;
        return stack.remove();
    }
}