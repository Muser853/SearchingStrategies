public final class IterativeDeepeningGreedySearch extends AbstractSearch{
    private final LinkedList<Cell> stack = new LinkedList<>();
    private final Boolean euclid;
    private int currentBest = 0;
    public int gap;

    public IterativeDeepeningGreedySearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.euclid = euclidean;
    }
    public void reset(){
        currentBest = 0;
        for(Cell cell: stack) cell.reset();
        stack.clear();
    }
    protected void addCell(Cell next){
        if (next.calculateHeuristics(euclid, next.g < 0 ? start : target) > currentBest)
            stack.addLast(next);
        else
            stack.addFirst(next);
    }
    protected void updateCell(Cell next) {
    }
    protected int numRemainingCells() {
        return stack.size();
    }
    protected Cell findNextCell(){
        cur = stack.remove();
        if (cur.calculateHeuristics(euclid,
                cur.g < 0 ? start : target) > currentBest)
            currentBest += gap;

        return cur;
    }
}