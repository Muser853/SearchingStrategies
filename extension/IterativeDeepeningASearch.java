import java.util.Comparator;

public final class IterativeDeepeningASearch extends AbstractSearch{
    private final PriorityQueue<Cell> heap;

    public IterativeDeepeningASearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);

        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell ->cell.calculateHeuristics(euclidean, cell.g < 0 ? start : target)
        + Math.abs(cell.g)
        )) : new Heap<>(Comparator.comparingInt(cell ->target.calculateHeuristics(euclidean, cell)
        + Math.abs(cell.g)
        ));
    }
    void reset(){
        for (Cell cell: heap) cell.reset();
        heap.clear();
    }
    int numRemainingCells(){
        return heap.size();
    }
    Cell findNextCell(){
        return heap.isEmpty() ? null : heap.poll();
    }
    void addCell(Cell cell){
        heap.offer(cell);
    }
    void updateCell(Cell cell){
        heap.updatePriority(cell);
    }
}