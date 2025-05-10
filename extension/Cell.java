import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.stream.Collectors;

public final class Cell {
    static final int[][] faceRotationIndices = {
        {4, 0, 5, 1}, // up
        {1, 5, 0, 4}, // down

        {2, 0, 3, 1}, // back
        {1, 3, 0, 2}, // front

        {1, 5, 6, 3}, // left
        {3, 6, 5, 1}  // right
    };
    // Constants for search direction
    static Cell origin = new Cell(new int[]{0, 3, 6, 9, 12, 15, 18});
    static Queue<Cell> ends;
    static List<Queue<Cell>> cellLevels;
    static {
        int[] newCoord, rotation, oneArray;
        int index, value;

        Cell polled;
        HashMap<Cell> cellMap = new HashMap<>(1<<24);
        List<Integer> coordList;
        Queue<Cell> currentLevel = new ArrayDeque<>(1<<23);
        currentLevel.add(origin);
        
        for (int size = 1; size!=0; size = currentLevel.size()){/*size:1
6
27
120
534
2348
10244
44241
186568
736513
2360094
4236655
2952910
491357
862*/
            for (int i = 0; i < size; i++){
                polled = currentLevel.poll();

                for (int j = 0; j < 6; j++){
                    newCoord = Arrays.copyOf(polled.coord, 7);
                    rotation = faceRotationIndices[2 + j % 2];
                    oneArray = faceRotationIndices[j];

                    for (int k = 0; k < 4; k++) {
                        newCoord[oneArray[rotation[k]]] =

                                (value = polled.coord[index = oneArray[k]] 
                                - polled.coord[index] % 3

                                + (polled.coord[index]*2) % 3 
                                - i / 2)

                                        < polled.coord[index]
                                        - polled.coord[index] % 3

                                        ? value + 3 : value;
                    }
                    if (!cellMap.containsKey(coordList = Arrays.stream(newCoord).boxed().collect(Collectors.toList()))) {
                        currentLevel.offer(
                            polled.neighbors[j] = new Cell(newCoord)
                        );
                        cellMap.put(coordList, polled.neighbors[j]);
                    } else {
                        polled.neighbors[j] = cellMap.get(coordList);
                    }
                }
            }
        }
    }
    final int[] coord;
    Cell prev; // previous cell in path
    final Cell[] neighbors = new Cell[6];
    Boolean forwardVisited = null; // default null, set each neighbor to true in the forward direction, set each neighbor to false in the reverse direction for bidirectional search
    int g = Integer.MAX_VALUE; // Distance from start/target node to current node
    
    Cell(int[] coord) {
        this.coord = coord;
    }
    void reset(){
        prev = null;
        forwardVisited = null;
        g = Integer.MAX_VALUE;
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