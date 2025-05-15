import java.util.Comparator;

public final class GreedySearch extends AbstractSearch{
    private final PriorityQueue<Cell> heap;

    public GreedySearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
                euclidean, cell.g < 0 ? start : target)));
    }
    public void reset(){
        for(Cell cell: heap) cell.reset();
        heap.clear();
    }
    protected void addCell(Cell next){
        heap.offer(next);
    }
    protected void updateCell(Cell next) {
        heap.updatePriority(next);
    }
    protected int numRemainingCells() {
        return heap.size();
    }
    protected Cell findNextCell(){
        return heap.poll();
    }
}