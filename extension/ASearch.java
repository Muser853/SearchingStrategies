import java.util.Comparator;

public final class ASearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;

    public ASearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
            euclidean, cell.g < 0 ? start : target) + Math.abs(cell.g)));
    }
    protected void addCell(Cell next){
        heap.offer(next);
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