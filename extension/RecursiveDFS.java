import java.util.LinkedList;

final class RecursiveDFS extends AbstractSearch {
    LinkedList<Cell> stack, path;

    RecursiveDFS(boolean bidirectional) {
        super(bidirectional);
        this.stack = new LinkedList<>();
    }
    @Override
    void reset(){
        for (Cell cell : stack) cell.reset();
        stack.clear();
    }
    @Override
    void addCell(Cell next){
        stack.push(next);
        if(next != start && next != target) {
            if (next.prev == start) search(next, target);
            else if (next.prev == target) search(start, next);
        }
    }
    @Override
    void updateCell(Cell next){
    }
    @Override
    int numRemainingCells() {return stack.size();}
    @Override
    Cell findNextCell() {
        return stack.isEmpty() ? null : stack.pop();
    }
}