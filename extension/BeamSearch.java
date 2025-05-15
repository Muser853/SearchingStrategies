import java.util.Comparator;

public class BeamSearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;
    public int beamWidth;

    public BeamSearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
            euclidean, cell.g < 0 ? start : target)));
    }
    public void reset(){
        for (Cell cell : heap) cell.reset();
        heap.clear();
    }
    protected void addCell(Cell next){// Only add cell within beam width
        if (heap.size() < beamWidth){
            heap.offer(next);
        }
        else if (heap.comparator().compare(next, heap.getLast()) < 0){
            explored.addFirst(heap.poll());
            heap.offer(next);
        }
    }
    protected void updateCell(Cell next) {
        heap.updatePriority(next);
    }
    protected int numRemainingCells(){
        return heap.size();
    }
    protected Cell findNextCell(){
        return heap.poll();
    }
}