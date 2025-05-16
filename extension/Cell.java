import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Cell {
    static final int[][] faceRotationIndices = {
        {4, 0, 5, 1}, // up
        {1, 5, 0, 4}, // down
        
        {2, 0, 3, 1}, // back
        {1, 3, 0, 2}, // front
        
        {1, 5, 6, 3}, // left
        {3, 6, 5, 1}// right
    };
    public final List<Integer> coord;
    public final List<Cell> neighbors = new ArrayList<>();
    Cell prev = null; // previous cell in path
    int g = 0; // Distance from start/target node to current node
    
    protected Cell(List<Integer> coord){
        this.coord = coord;
    }
    public void reset(){
        prev = null;
        g = 0;
    }

    protected static ArrayList<LinkedList<Cell>> generateCellLevels(){
        final ArrayList<LinkedList<Cell>> cellLevels = new ArrayList<>();
        final List<Integer> origin = List.of (0, 3, 6, 9, 12, 15, 18);
        
        HashMap<List<Integer>, Cell> cellMap = new HashMap<>(1<<24);
        cellMap.put(origin, new Cell(origin));
        
        LinkedList<Cell> ends = new LinkedList<>();
        ends.addFirst(cellMap.get(origin));
        do{
            cellLevels.add(new LinkedList<>(ends));
            List<Integer> newCoord;
            Cell polled;
            int size = ends.size();
            for (int i = 0; i < size; i++){
                polled = ends.remove();
                for (int j = 0; j < 2; j++){
                    newCoord = new ArrayList<>(polled.coord);
                    int[] rotation = faceRotationIndices[2 + j % 2];
                    int[] oneArray = faceRotationIndices[j];
                    int d;
                    int v;
                    for (int k = 0; k < 4; k++){
                        v = (d = polled.coord.get(oneArray[k])) - d % 3
                                + (d * 2) % 3 - i / 2;
                        newCoord.set(oneArray[rotation[k]], v < d - d % 3
                                ? v + 3 : v);
                    }
                    cellMap.computeIfAbsent(newCoord, key -> {
                        ends.addLast(new Cell(key));
                        return ends.getLast();
                    });
                    polled.neighbors.add(cellMap.get(newCoord));
                }
            }
        }while (!ends.isEmpty());
        return cellLevels;
    }

    public int calculateHeuristics(Boolean euclidean, Cell target){
        int sum = 0;
        if (euclidean == null) for (int i = 0; i < coord.size(); i++){
            if (! coord.get(i).equals(target.coord.get(i))) sum++;
        }
        else if (euclidean) for (int i = 0; i < coord.size(); i++){
            sum += (coord.get(i) - target.coord.get(i)) * (coord.get(i) - target.coord.get(i));
        }
        else for (int i = 0; i < coord.size(); i++){
            sum += Math.abs(coord.get(i) - target.coord.get(i));
        }
        return sum;
    }
    @Override
    public boolean equals(Object o){
        return this == o || coord.equals(((Cell)o).coord);
    }
    @Override
    public int hashCode(){
        return coord.hashCode();
    }
    @Override
    public String toString(){
        return coord.toString();
    }
}