import java.util.Comparator;

public final class RecursiveGreedySearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;

    public RecursiveGreedySearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = new Heap<>(Comparator.comparingInt(cell -> cell.calculateHeuristics(
            euclidean, cell.g < 0 ? start : target)));
    }
    void reset(){
        for(Cell cell : heap) cell.reset();
        heap.clear();
    }
    public void addCell(Cell next){
        heap.offer(next);
        if (next != start && next != target) {
            if (next.prev == start) {
                for (Cell nextNeighbors : next.neighbors) {
                    if (nextNeighbors != next.prev && nextNeighbors.g == 0)
                        search(nextNeighbors, target);
                }
            }else if (next.prev == target) {
                for (Cell nextNeighbors : next.neighbors) {
                    if (nextNeighbors != next.prev && nextNeighbors.g == 0)
                        search(start, nextNeighbors);
                }
            }
        }
    }
    public void updateCell(Cell next){
        heap.updatePriority(next);
    }
    public int numRemainingCells(){
        return heap.size();
    }
    public Cell findNextCell(){
        return heap.poll();
    }
}