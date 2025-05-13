import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

public final class Cell {
    static final int[][] faceRotationIndices = {
        {4, 0, 5, 1}, // up
        {1, 5, 0, 4}, // down

        {2, 0, 3, 1}, // back
        {1, 3, 0, 2}, // front

        {1, 5, 6, 3}, // left
        {3, 6, 5, 1}  // right
    };
    public static final Cell origin = new Cell(new int[]{0, 3, 6, 9, 12, 15, 18});
    public static final List<Queue<Cell>> cellLevels = new ArrayList<>();
    public int depth = 0; // Track depth in the search tree
    static {
        int[] newCoord, rotation, oneArray;
        int index;
        int value;
        Cell cell;
        Cell polled;
        HashSet<Cell> cellSet = new HashSet<>();
        cellSet.add(origin);
        Queue<Cell> ends = new ArrayDeque<>();
        ends.offer(origin);

        for (int size = 1; size > 0; size = ends.size()) {
            cellLevels.add(ends);
            for (int i = 0; i < size; i++) {
                polled = ends.poll();
                for (int j = 0; j < 6; j++) {
                    newCoord = Arrays.copyOf(polled.coord, 7);
                    rotation = faceRotationIndices[2 + j % 2];
                    oneArray = faceRotationIndices[j];

                    for (int k = 0; k < 4; k++) {
                        newCoord[oneArray[rotation[k]]] =
                                (value = polled.coord[index = oneArray[k]] - polled.coord[index] % 3
                                        + (polled.coord[index] * 2) % 3
                                        - i / 2
                                ) < polled.coord[index] - polled.coord[index] % 3

                                        ? value + 3 : value;
                    }
                    if (cellSet.contains(
                            cell = new Cell(newCoord)
                    )) ends.offer(cell);

                    polled.neighbors[j] = cellSet.put(cell);
                }
            }
        }
    }
    protected final int[] coord;
    public final Cell[] neighbors = new Cell[6];
    Cell prev = null; // previous cell in path
    int g = 0; // Distance from start/target node to current node
    
    protected Cell(int[] coord) {
        this.coord = coord;
    }
    public void reset(){
        prev = null;
        g = 0;
    }
    public int calculateHeuristics(Boolean euclidean, Cell target) {
        int sum = 0;
        if (euclidean == null) {
            for (int i = 0; i < coord.length; i++) {
                if(coord[i] != target.coord[i]) sum++;
            }
        }else if (euclidean) {
            for (int i = 0; i < coord.length; i++) {
                sum += (coord[i] - target.coord[i]) * (coord[i] - target.coord[i]);
            }
        }else{
            for (int i = 0; i < coord.length; i++) {
                sum += Math.abs(coord[i] - target.coord[i]);
            }
        }return sum;
    }
    @Override
    public boolean equals(Object o) {return this == o || Arrays.equals(coord, ((Cell)o).coord);}
    @Override
    public int hashCode() {return Arrays.hashCode(coord);}
    @Override
    public String toString() {return Arrays.toString(coord);}
}