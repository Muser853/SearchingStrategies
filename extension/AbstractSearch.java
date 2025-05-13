public abstract class AbstractSearch {
    public Cell start, target, cur;
    protected final LinkedList<Cell> explored;
    protected final LinkedList<Cell> path;
    protected final boolean bidirectional;

    public AbstractSearch(boolean bidirectional) {
        this.bidirectional = bidirectional;
        this.path = new LinkedList<>();
        this.explored = new LinkedList<>();
    }
    abstract void reset();
    abstract void addCell(Cell next);
    abstract void updateCell(Cell next);
    abstract int numRemainingCells();
    abstract Cell findNextCell();

    final LinkedList<Cell> traceback(Cell cell){
        for(path.addFirst(cell); path.getFirst().prev != null; path.addFirst(path.getFirst().prev)){
        }
        for(path.addLast(cur); path.getLast().prev != null; path.addLast(path.getLast().prev)){
        }
        return path;
    }
   
    protected final LinkedList<Cell> search(Cell start, Cell target) {
        this.start = start;
        this.target = target;
        addCell(start);
        if(bidirectional) addCell(target);

        while (numRemainingCells() > 0) {
            if((cur = findNextCell()) == null) break;
            explored.addFirst(cur);

            for (Cell neighbor : cur.neighbors) {
                if (neighbor.g == 0){
                    neighbor.g = cur.g + Integer.signum(cur.g);
                    neighbor.prev = cur;

                    addCell(neighbor);
                }
                else if (Integer.signum(cur.g) != Integer.signum(neighbor.g))
                    return traceback(neighbor);

                else if (Math.abs(cur.g) +1 < Math.abs(neighbor.g)) {
                    neighbor.g = cur.g + Integer.signum(cur.g);
                    neighbor.prev = cur;

                    updateCell(neighbor);
                }
            }
        }return null;
    }
}