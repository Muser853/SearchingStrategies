import java.util.Comparator;

public final class ASearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;

    public ASearch(Boolean euclidean, boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
            euclidean, cell.g < 0 ? start : target) + Math.abs(cell.g)));
    }
    protected boolean addCell(Cell next){
        heap.offer(next);
        return true;
    }
    protected void updateCell(Cell next){
        heap.updatePriority(next);
    }
    public int numRemainingCells(){
        return heap.size();
    }
    protected Cell findNextCell(){
        return heap.isEmpty() ? null : heap.poll();
    }
    public void reset(){
        for (Cell cell : heap) cell.reset();
        heap.clear();
    }
}