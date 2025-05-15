public final class SearchSimulation{
    private static int totalPathLength = 0;
    private static int totalStored = 0;
    private static int totalNextFound = 0;
    private static int cellCount = 0;
    private static final Boolean[] euclidean = {Boolean.FALSE, null, Boolean.TRUE};
    private static final boolean[] bidirectional = {false, true};

    static final void experimentFor(AbstractSearch searcher){
        System.out.printf("%s %s\n", searcher.bidirectional ?
        "bidirectional" : "unidirectional", searcher.getClass().getSimpleName());
        //CSV Header
        for (int i = 0; i < Cell.cellLevels.size(); i++){
            for (int j = 0; j < Cell.cellLevels.size(); j++){
                for (Cell start : Cell.cellLevels.get(i)){
                    for (Cell target : Cell.cellLevels.get(j)){
                        if (start != target){
                            for (Cell cell : searcher.explored) cell.reset();
                            searcher.explored.clear();
                            searcher.reset();
                            start.g = 1;
                            target.g = -1;
                            totalPathLength += searcher.search(start, target).size();
                            totalNextFound += searcher.explored.size();
                            totalStored += searcher.numRemainingCells();
                            cellCount++;
                        }
                    }
                }System.out.printf("from level %d to level %d: pathLength: %d, stored: %d, nextFound: %d\n cellCount: %d\n averagePathLength: %.4f, averageCellsStored: %.4f, averageNextFound: %.4f\n",
                        i, j, totalPathLength, totalStored, totalNextFound, cellCount,
                        totalPathLength/(double)cellCount, totalStored/(double)cellCount, totalNextFound/(double)cellCount);
                totalPathLength = totalStored = totalNextFound = cellCount = 0;
            }
        }
    }
    public static void main(String[] args){
        DepthLimitedDFS dLDFSearcher;
        RecursiveDepthLimitedDFS recursiveDLDFSearcher;

        IterativeDeepeningDFS iterativeDeepeningSearcher;
        RecursiveIDDFS recursiveIDDFSearcher;

        IterativeDeepeningGreedySearch iterativeDeepeningGreedySearcher;
        RecursiveIterativeDeepeningGreedySearch recursiveIterativeDeepeningGreedySearch;

        IterativeDeepeningASearch iterativeDeepeningASearcher;
        RecursiveIDASearch recursiveIDASearcher;

        BeamSearch beamSearcher;
        RecursiveBeamSearch recursiveBeamSearcher;

        for (boolean directional : bidirectional){
            experimentFor(new BreadthFirstSearch(directional));
            experimentFor(new RecursiveBreadthFirstSearch(directional));

            experimentFor(new DepthFirstSearch(directional));
            experimentFor(new RecursiveDFS(directional));

            dLDFSearcher = new DepthLimitedDFS(directional);
            recursiveDLDFSearcher = new RecursiveDepthLimitedDFS(directional);
            for (int max = 1; max < 64; max++){
                dLDFSearcher.depthLimit = recursiveDLDFSearcher.depthLimit = max;
                System.out.printf("maxDepth: %d\n", max);
                experimentFor(dLDFSearcher);
                experimentFor(recursiveDLDFSearcher);
            }

            iterativeDeepeningSearcher = new IterativeDeepeningDFS(directional);
            recursiveIDDFSearcher = new RecursiveIDDFS(directional);
            for (int gap = 1; gap < 16; gap++){
                iterativeDeepeningSearcher.gap = recursiveIDDFSearcher.gap = gap;
                System.out.printf("gap: %d\n", gap);
                experimentFor(iterativeDeepeningSearcher);
                experimentFor(recursiveIDDFSearcher);
            }

            for(Boolean euclidean : euclidean){
                experimentFor(new GreedySearch(euclidean, directional));
                experimentFor(new RecursiveGreedySearch(euclidean, directional));

                iterativeDeepeningGreedySearcher = new IterativeDeepeningGreedySearch(euclidean, directional);
                recursiveIterativeDeepeningGreedySearch = new RecursiveIterativeDeepeningGreedySearch(euclidean, directional);
                for (int gap = 1; gap < 16; gap++){
                    iterativeDeepeningGreedySearcher.gap = recursiveIterativeDeepeningGreedySearch.gap = gap;
                    System.out.printf("gap: %d\n", gap);
                    experimentFor(iterativeDeepeningGreedySearcher);
                    experimentFor(recursiveIterativeDeepeningGreedySearch);
                }

                experimentFor(new ASearch(euclidean, directional));
                experimentFor(new RecursiveASearch(euclidean, directional));

                iterativeDeepeningASearcher = new IterativeDeepeningASearch(euclidean, directional);
                recursiveIDASearcher = new RecursiveIDASearch(euclidean, directional);
                for (int gap = 1; gap < 32; gap++){
                    iterativeDeepeningASearcher.gap = recursiveIDASearcher.gap = gap;
                    System.out.printf("gap: %d\n", gap);
                    experimentFor(iterativeDeepeningASearcher);
                    experimentFor(recursiveIDASearcher);
                }

                beamSearcher = new BeamSearch(euclidean, directional);
                recursiveBeamSearcher = new RecursiveBeamSearch(euclidean, directional);
                for(int max = 1; max < 64; max++){
                    beamSearcher.beamWidth = recursiveBeamSearcher.beamWidth = max;
                    System.out.printf("beamWidth: %d\n", max);
                    experimentFor(beamSearcher);
                    experimentFor(recursiveBeamSearcher);
                }
            }
        }
    }
}