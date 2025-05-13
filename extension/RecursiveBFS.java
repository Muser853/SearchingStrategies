import java.util.LinkedList;
import java.util.Queue;

public class RecursiveBFS extends AbstractSearch {
    private final Queue<Cell> queue;
    
    public RecursiveBFS(boolean bidirectional) {
        super(bidirectional);
        this.queue = new LinkedList<>();
    }
    void reset(){
        for(Cell cell : queue) cell.reset();
        this.queue.clear();
    }
    public void addCell(Cell next){
        queue.add(next);
        if(next != start && next != target){
            if(next.prev == start) search(next, target);
            else if(next.prev == target) search(start, next);
        }
    }
    public void updateCell(Cell next){
    }
    public int numRemainingCells(){return queue.size();}
    
    public Cell findNextCell(){
        return queue.isEmpty() ? null : queue.poll();
    }
}