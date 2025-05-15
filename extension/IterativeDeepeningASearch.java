public final class IterativeDeepeningASearch extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();
    private final Boolean euclid;
    private int currentFCost = 0;
    public int gap;

    public IterativeDeepeningASearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.euclid = euclidean;
    }
    public void reset(){
        currentFCost = 0;
        for (Cell cell : stack) cell.reset();
        stack.clear();
    }
    protected void addCell(Cell next){
        if (Math.abs(next.g) + next.calculateHeuristics(euclid,
        next.g < 0 ? start : target) > currentFCost) return;

        stack.addFirst(next);
        if (next != start && next != target) {
            if (next.prev == start) {
                for (Cell neighbor : next.neighbors) {
                    if (neighbor != next.prev && neighbor.g == 0) {
                        search(neighbor, target);
                    }
                }
            } else if (next.prev == target) {
                for (Cell neighbor : next.neighbors) {
                    if (neighbor != next.prev && neighbor.g == 0) {
                        search(start, neighbor);
                    }
                }
            }
        }
    }
    protected void updateCell(Cell next){
    }
    protected int numRemainingCells(){
        return stack.size();
    }
    protected Cell findNextCell(){
        cur = stack.remove();
        if (Math.abs(cur.g)+cur.calculateHeuristics(euclid,
                cur.g < 0 ? start : target) > currentFCost)// No more cells at current f-cost limit
            currentFCost += gap;

        return cur;
    }
}