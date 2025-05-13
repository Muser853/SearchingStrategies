public final class RecursiveIDDFS extends AbstractSearch {
    private int depthLimit; // Current depth limit for the depth-limited search

    public RecursiveIDDFS(boolean bidirectional){
        super(bidirectional);
        this.depthLimit = 0;
    }
    @Override
    void reset(){
        depthLimit = 0;
    }
    @Override
    void addCell(Cell next){
        explored.addFirst(next);

        if (Math.abs(next.g) <= depthLimit)
            next.prev = cur;
        if (next != start && next != target){
            if (next.prev == start) search(next, target);
            else if (next.prev == target) search(start, next);
        }
    }
    @Override
    void updateCell(Cell next) {
    }
    @Override
    int numRemainingCells() {
        return depthLimit;
    }
    @Override
    Cell findNextCell() {
        return depthLimit == 0 ? null : start;
    }
}