public final class RecursiveDFS extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();

    public RecursiveDFS(boolean bidirectional){
        super(bidirectional);
    }
    public void reset(){
        for (Cell cell : stack) cell.reset();
        stack.clear();
    }
    protected void addCell(Cell next){
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
    protected void updateCell(Cell next){
    }
    public int numRemainingCells() {
        return stack.size();
    }
    protected Cell findNextCell() {
        return stack.remove();
    }
}