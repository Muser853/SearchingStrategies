public abstract class AbstractSearch {
    public Cell start, target, cur;
    protected final LinkedList<Cell> explored = new LinkedList<>();
    protected LinkedList<Cell> path = new LinkedList<>();
    protected final boolean bidirectional;
    protected final boolean recursive;

    public AbstractSearch(boolean bidirectional, boolean recursive){
        this.bidirectional = bidirectional;
        this.recursive = recursive;
    }
    abstract void reset();
    abstract boolean addCell(Cell next);
    abstract void updateCell(Cell next);
    abstract int numRemainingCells();
    abstract Cell findNextCell();

    final LinkedList<Cell> traceback(Cell cell){
        LinkedList<Cell> pas = new LinkedList<>();
        for(pas.addFirst(cell); pas.getFirst().prev != null; pas.addFirst(pas.getFirst().prev)){
        }
        for(pas.addLast(cur); pas.getLast().prev != null && !pas.getLast().prev.equals(cur); pas.addLast(pas.getLast().prev)){
        }
        return pas;
    }
    public final LinkedList<Cell> search(Cell start, Cell target){
        this.start = start;
        this.target = target;
        addCell(start);
        if (bidirectional) addCell(target);

        while (numRemainingCells() > 0){
            explored.addFirst((cur = findNextCell()));

            for (Cell neighbor : cur.neighbors){
                if (neighbor.g == 0){
                    neighbor.g = cur.g + Integer.signum(cur.g);
                    neighbor.prev = cur;

                    if (addCell(neighbor) && recursive){
                        if (neighbor.prev == start){
                            for (Cell nextNeighbors : neighbor.neighbors){
                                if (nextNeighbors != neighbor.prev && nextNeighbors.g == 0)
                                    search(nextNeighbors, target);
                            }
                        }else if (neighbor.prev == target){
                            for (Cell nextNeighbors : neighbor.neighbors){
                                if (nextNeighbors != neighbor.prev && nextNeighbors.g == 0)
                                    search(start, nextNeighbors);
                            }
                        }
                    }
                }
                else if (Integer.signum(cur.g) != Integer.signum(neighbor.g))
                    return traceback(neighbor);

                else if (Math.abs(cur.g) +1 < Math.abs(neighbor.g)){
                    neighbor.g = cur.g + Integer.signum(cur.g);
                    neighbor.prev = cur;

                    updateCell(neighbor);
                }
            }
        }return path;
    }
}