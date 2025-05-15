import java.util.Comparator;

public final class RecursiveASearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;

    public RecursiveASearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> Math.abs(cell.g) + cell.calculateHeuristics(
                euclidean, cell.g < 0 ? start : target)));
    }
    public void reset(){
        for (Cell cell: heap) cell.reset();
        heap.clear();
    }
    protected void addCell(Cell next) {
        heap.offer(next);
        if (next != start && next != target){
            if (next.prev == start){
                for (Cell nextNeighbors : next.neighbors){
                    if (nextNeighbors != next.prev && nextNeighbors.g == 0)
                        search(nextNeighbors, target);
                }
            }else if (next.prev == target){
                for (Cell nextNeighbors : next.neighbors){
                    if (nextNeighbors != next.prev && nextNeighbors.g == 0)
                        search(start, nextNeighbors);
                }
            }
        }
    }
    protected void updateCell(Cell next){
        heap.updatePriority(next);
    }
    protected int numRemainingCells(){
        return heap.size();
    }
    protected Cell findNextCell(){
        return heap.poll();
    }
}