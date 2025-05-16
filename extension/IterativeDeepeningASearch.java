public class IterativeDeepeningASearch extends AbstractSearch {
    private final LinkedList<Cell> stack = new LinkedList<>();
    private final Boolean euclid;
    private int currentFCost = 0;
    public int gap;

    public IterativeDeepeningASearch(Boolean euclidean, boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
        this.euclid = euclidean;
    }
    public void reset(){
        currentFCost = 0;
        for (Cell cell : stack) cell.reset();
        stack.clear();
    }
    protected boolean addCell(Cell next){
        if (Math.abs(next.g) + 
        next.calculateHeuristics(euclid, next.g < 0 ? start : target) > currentFCost){
            stack.addLast(next);
            return false;
        }
        stack.addFirst(next);
        return true;    
    }
    protected void updateCell(Cell next){
        if (next.calculateHeuristics(euclid, next.g < 0 ? start : target) <= currentFCost){
            stack.addFirst(stack.remove(next));
        }
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