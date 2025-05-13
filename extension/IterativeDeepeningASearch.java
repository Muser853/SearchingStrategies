import java.util.Comparator;

public final class IterativeDeepeningASearch extends AbstractSearch{
    private final PriorityQueue<Cell> heap;

    public IterativeDeepeningASearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);
        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell ->target.calculateHeuristics(euclidean, cell)
        + (cur.prev == null ? 0 : cur.prev.g + 1)
        )) : new Heap<>(Comparator.comparingInt(cell ->target.calculateHeuristics(euclidean, cell)));
    }
    void reset(){
        for (Cell cell: heap) cell.reset();
        heap.clear();
    }
    @Override
    int numRemainingCells(){return heap.size();}
    @Override
    Cell findNextCell(){return heap.poll();}
    @Override
    void addCell(Cell cell){
        heap.offer(cell);
    }
    @Override
    void updateCell(Cell cell){heap.updatePriority(cell);}
}