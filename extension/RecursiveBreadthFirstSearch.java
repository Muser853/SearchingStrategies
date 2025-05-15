public class RecursiveBreadthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> queue;
    
    public RecursiveBreadthFirstSearch(boolean bidirectional) {
        super(bidirectional);
        this.queue = new LinkedList<>();
    }
    void reset(){
        for(Cell cell : queue) cell.reset();
        this.queue.clear();
    }
    public void addCell(Cell next){
        queue.addLast(next);
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
    public void updateCell(Cell next){
    }
    public int numRemainingCells(){
        return queue.size();
    }
    public Cell findNextCell(){
        return queue.remove();
    }
}