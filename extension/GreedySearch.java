import java.util.Comparator;

public final class GreedySearch extends AbstractSearch{
    private final PriorityQueue<Cell> heap;

    public GreedySearch(Boolean euclidean, boolean bidirectional, boolean recursive){
        super(bidirectional, recursive);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
                euclidean, cell.g < 0 ? start : target)));
    }
    public void reset(){
        for(Cell cell: heap) cell.reset();
        heap.clear();
    }
    protected boolean addCell(Cell next){
        heap.offer(next);
        return true;
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