import java.util.Comparator;

public class BeamSearch extends AbstractSearch{
    private final PriorityQueue<Cell> heap;

    public BeamSearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);

        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
                euclidean, cell.g < 0 ? start : target))) : new Heap<>(Comparator.comparingInt(cell ->
                target.calculateHeuristics(euclidean, cell)));
    }
    public void reset(){
        for (Cell cell : heap) cell.reset();
        heap.clear();
    }
    public void addCell(Cell next){
        heap.offer(next);
    }
    public void updateCell(Cell next) {
        heap.updatePriority(next);
    }
    public int numRemainingCells() {
        return heap.size();
    }
    public Cell findNextCell() {
        return heap.isEmpty() ? null : heap.poll();
    }
}