public final class RecursiveIterativeDeepeningGreedySearch extends AbstractSearch{
    private final LinkedList<Cell> stack;
    private final Boolean euclid;
    private int currentBest = 0;
    public int gap;

    public RecursiveIterativeDeepeningGreedySearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.euclid = euclidean;
        this.stack = new LinkedList<>();
    }
    public void reset(){
        for(Cell cell: stack) cell.reset();
        stack.clear();
    }
    protected void addCell(Cell next){
        if (next.calculateHeuristics(euclid,
                next.g < 0 ? start : target) > currentBest)
            stack.addLast(next);
        else
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
    protected void updateCell(Cell next) {
    }
    protected int numRemainingCells() {
        return stack.size();
    }
    protected Cell findNextCell(){
        Cell cur = stack.remove();
        if (cur.calculateHeuristics(euclid, cur.g < 0 ? start : target) > currentBest)
            currentBest += gap;

        return cur;
    }
}