import java.util.Comparator;

public final class ASearch extends AbstractMazeSearch {
    private final PriorityQueue<Cell> openSet;

    public ASearch(Boolean euclidean, boolean bidirectional) {
        super(bidirectional);

        this.openSet = new Heap<>(Comparator.comparingInt(
            cell -> Math.min(
                cell.g  + cell.calculateHeuristics(euclidean, target),
                Integer.MAX_VALUE)
            ));
    }

    @Override
    void addCell(Cell next) {
        next.g = cur.g + 1;
        openSet.offer(next);
        exploredCells.add(next);
    }
    @Override
    void updateCell(Cell next) {
        openSet.updatePriority(next);
    }

    @Override
    int numRemainingCells() {
        return openSet.size();
    }

    @Override
    Cell findNextCell() {
        if (openSet.isEmpty()) return null;

        cur = openSet.poll();

        for (Cell neighbor : cur.neighbors) {
            if (neighbor.prev == null) {
                neighbor.prev = cur;
                if (cur.g + 1 < neighbor.g) {
                    neighbor.g = cur.g + 1;
                    addCell(neighbor);
                }
            }
            else if (cur.g + 1 < neighbor.g) {
                neighbor.prev = cur;
                updateCell(neighbor);
            }
        }return cur;
    }
    @Override
    void reset(){
        super.reset();
        openSet.clear();
    }

    @Override
    boolean updatePath(Cell neighbor) {
        if (cur.g + 1 < neighbor.g) {
            neighbor.prev = cur;
            neighbor.g = cur.g + 1;
            return true;
        }
        return false;
    }
}