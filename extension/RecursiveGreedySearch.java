import java.util.Comparator;

public final class RecursiveGreedySearch extends AbstractMazeSearch {
    private final PriorityQueue<Cell> priorityQueue;

    public RecursiveGreedySearch(Boolean euclidean, boolean bidirectional) {
        super(bidirectional);
        this.priorityQueue = new Heap<>(Comparator.comparingInt(cell ->
        target.calculateHeuristics(euclidean, cell)));
    }
    @Override
    public void addCell(Cell next){}
    @Override
    public boolean updatePath(Cell neighbor){return false;}
    @Override
    public void updateCell(Cell next) {priorityQueue.updatePriority(next);}
    @Override
    public int numRemainingCells() {return priorityQueue.size();}
    @Override
    public Cell findNextCell() {return null;}

    private boolean recursiveGreedySearch(int depth) {
        if (depth <= 0 || priorityQueue.isEmpty()) {
            return false;
        }
        cur = priorityQueue.poll();
        addCell(cur);
        if (cur.equals(target)) {
            return true;
        }
        // Add unvisited neighbors to priority queue
        for (Cell neighbor : cur.neighbors) {
            if (neighbor.prev == null) {
                neighbor.prev = cur;
                priorityQueue.offer(neighbor);
            }
            else if (cur.g +1 < neighbor.g){
                neighbor.prev = cur;
                updateCell(neighbor);
            }
            if(neighbor.forwardVisited != cur.forwardVisited){
                return true;
            }
        }return recursiveGreedySearch(depth - 1);
    }// priorityQueue.offer(start);
    // if (bidirectional) priorityQueue.offer(target);
    // boolean found = false;
    // // Iterative deepening greedy search
    // for (int depth = 0; !found && !priorityQueue.isEmpty()&& depth < 256; depth++) {
    //     found = recursiveGreedySearch(depth);
    // }
    // return found ? traceback(target) : null;
}