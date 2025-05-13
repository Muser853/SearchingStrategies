import java.util.Comparator;

public final class ASearch extends AbstractSearch {
    private final PriorityQueue<Cell> heap;

    public ASearch(Boolean euclidean, boolean bidirectional){
        super(bidirectional);

        this.heap = bidirectional ? new Heap<>(Comparator.comparingInt(cell -> Math.abs(cell.g) + cell.calculateHeuristics(
                euclidean, cell.g < 0 ? target : start))) : new Heap<>(Comparator.comparingInt(cell ->
                Math.abs(cell.g) + target.calculateHeuristics(euclidean, cell)));
    }
    @Override
    void addCell(Cell next){
        heap.offer(next);
    }
    @Override
    void updateCell(Cell next){
        heap.updatePriority(next);
    }
    @Override
    int numRemainingCells(){
        return heap.size();
    }
    @Override
    Cell findNextCell(){
        return heap.isEmpty() ? null : heap.poll();
    }
    void reset(){
        for (Cell cell : heap) cell.reset();
        heap.clear();
    }
}