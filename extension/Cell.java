import java.util.List;
import java.util.ArrayList;
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
    public static final ArrayList<LinkedList<Cell>> cellLevels = new ArrayList<>();
    static{
        final ArrayList<Integer> origin = new ArrayList<>(List.of(0,3,6,9,12,15,18));
        ArrayList<Integer> newCoord;
        int[] rotation;
        int[] oneArray;
        int index;
        int value;
        Cell polled;
        HashMap<List<Integer>, Cell> cellMap = new HashMap<>();
        cellMap.put(origin, new Cell(origin));
        LinkedList<Cell> ends = new LinkedList<>();
        ends.addFirst(cellMap.get(origin));

        for (int size = 1; size > 0; size = ends.size()){
            cellLevels.add(new LinkedList<Cell>(ends));
            for (int i = 0; i < size; i++){
                polled = ends.remove();
                for (int j = 0; j < 6; j++){
                    newCoord = new ArrayList<>(polled.coord);
                    rotation = faceRotationIndices[2 + j % 2];
                    oneArray = faceRotationIndices[j];
                    for (int k = 0; k < 4; k++){
                        newCoord.set(oneArray[rotation[k]],
                                (value = polled.coord.get(index = oneArray[k]) - polled.coord.get(index) % 3
                                        + (polled.coord.get(index) * 2) % 3
                                        - i / 2
                                ) < polled.coord.get(index) - polled.coord.get(index) % 3

                                        ? value + 3 : value);
                    }
                    ends.addLast(cellMap.putIfAbsent(newCoord, new Cell(newCoord)));
                    polled.neighbors[j] = cellMap.get(newCoord);
                }
            }
        }
    }
    protected final ArrayList<Integer> coord;
    public final Cell[] neighbors = new Cell[6];
    Cell prev = null; // previous cell in path
    int g = 0; // Distance from start/target node to current node
    
    protected Cell(ArrayList<Integer> coord){
        this.coord = coord;
    }
    public void reset(){
        prev = null;
        g = 0;
    }
    public int calculateHeuristics(Boolean euclidean, Cell target){
        int sum = 0;
        if (euclidean == null){
            for (int i = 0; i < coord.size(); i++){
                if (coord.get(i) != target.coord.get(i)) sum++;
            }
        }else if (euclidean){
            for (int i = 0; i < coord.size(); i++){
                sum += (coord.get(i) - target.coord.get(i)) * (coord.get(i) - target.coord.get(i));
            }
        }else{
            for (int i = 0; i < coord.size(); i++){
                sum += Math.abs(coord.get(i) - target.coord.get(i));
            }
        }return sum;
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