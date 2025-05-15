public final class BreadthFirstSearch extends AbstractSearch {
    private final LinkedList<Cell> queue = new LinkedList<>();
    
    public BreadthFirstSearch(boolean bidirectional){
        super(bidirectional);
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