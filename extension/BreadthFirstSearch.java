public final class BreadthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> queue;

    public BreadthFirstSearch(boolean bidirectional){
        super(bidirectional);
        this.queue = new LinkedList<>();
    }
    void reset(){
        for (Cell cell : queue) cell.reset();
        queue.clear();
    }
    @Override
    void addCell(Cell next) {
        queue.addLast(next);
    }
    @Override
    void updateCell(Cell next) {}

    @Override
    int numRemainingCells() {
        return queue.size();
    }

    @Override
    Cell findNextCell(){
        return queue.remove();
    }
}