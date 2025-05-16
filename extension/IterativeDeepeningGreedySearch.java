public final class IterativeDeepeningGreedySearch extends AbstractSearch{
    private final LinkedList<Cell> stack = new LinkedList<>();
    private final Boolean euclid;
    private int currentBest = 0;
    public int gap;

    public IterativeDeepeningGreedySearch(Boolean euclidean, boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
        this.euclid = euclidean;
    }
    public void reset(){
        currentBest = 0;
        for(Cell cell: stack) cell.reset();
        stack.clear();
    }
    protected boolean addCell(Cell next){
        if (next.calculateHeuristics(euclid, next.g < 0 ? start : target) > currentBest){
            stack.addLast(next);
            return false;
        }
        stack.addFirst(next);
        return true;
    }
    protected void updateCell(Cell next){
        if (next.calculateHeuristics(euclid, next.g < 0 ? start : target) <= currentBest){
            stack.addFirst(stack.remove(next));
        }
    }
    protected Cell findNextCell(){
        cur = stack.remove();
        if (cur.calculateHeuristics(euclid, cur.g < 0 ? start : target) > currentBest){
            currentBest += gap;
        }
        return cur;
    }
    public int numRemainingCells() {
        return stack.size();
    }
}