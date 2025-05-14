public final class SearchSimulation{
    private static int totalPathLength = 0;
    private static int totalStored = 0;
    private static int totalNextFound = 0;
    private static LinkedList<Cell> path;
    private static final Boolean[] euclidean = new Boolean[]{Boolean.FALSE, null, Boolean.TRUE};
    private static final int[] beamWidth = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int[] cellLevels = new int[]{0, 1, 2, 3, Cell.cellLevels.size()-1};

    static final void experimentFor(AbstractSearch searcher){
        for (int i : cellLevels)
        for (int j : cellLevels)
        for (Cell start : Cell.cellLevels.get(i))
        for (Cell target:Cell.cellLevels.get(j)){
            if (start != target){
                for(Cell cell : searcher.explored) cell.reset();
                searcher.explored.clear();
                searcher.reset();
                start.g = 1;
                target.g = -1;
                totalPathLength += (path = searcher.search(start, target))
                    == null ? 0 : path.size();
                totalNextFound += searcher.explored.size();
                totalStored += searcher.numRemainingCells();
            }
            System.out.printf("%s %s from level %d to level %d: pathLength: %d, stored: %d, nextFound: %d\n",
            searcher.bidirectional ? "bidirectional" : "unidirectional", searcher.getClass().getSimpleName(),
            i, j, totalPathLength, totalStored, totalNextFound);
            totalPathLength = totalStored = totalNextFound = 0;
        }
    }
    public static void main(String[] args) {
        System.out.println("algorithm, total_path_length, total_cells_stored, total_next_found");
        // CSV header
        for (boolean directional : new boolean[]{false, true}){
            experimentFor(new BreadthFirstSearch(directional));
            experimentFor(new RecursiveBFS(directional));

            experimentFor(new DepthFirstSearch(directional));
            experimentFor(new RecursiveDFS(directional));
            experimentFor(new IterativeDeepeningDepthFirstSearch(directional));
            experimentFor(new RecursiveIDDFS(directional));

            for(Boolean euclidean : euclidean){
                experimentFor(new GreedySearch(euclidean, directional));
                experimentFor(new RecursiveGreedySearch(euclidean, directional));

                experimentFor(new ASearch(euclidean, directional));
                experimentFor(new RecursiveASearch(euclidean, directional));
                experimentFor(new IterativeDeepeningASearch(euclidean, directional));
                experimentFor(new RecursiveIDASearch(euclidean, directional));

                for(int beamWidth : beamWidth){
                    experimentFor(new BeamSearch(euclidean, directional, beamWidth));
                    experimentFor(new RecursiveBeamSearch(euclidean, directional, beamWidth));
                }
            }
        }
    }
}