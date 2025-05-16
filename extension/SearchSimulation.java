import java.util.ArrayList;

public class SearchSimulation{
    private static int totalPathLength = 0;
    private static int totalStored = 0;
    private static int totalNextFound = 0;
    private static int cellCount = 0;
    private static final Boolean[] euclidean = {Boolean.FALSE, null, Boolean.TRUE};
    private static final ArrayList<LinkedList<Cell>> cellLevels = Cell.generateCellLevels();

    static void experimentFor(AbstractSearch searcher){
        System.out.printf("%s %s\n", searcher.bidirectional ?
        "bidirectional":"unidirectional", searcher.getClass().getSimpleName());
        //CSV Header
        for (int i = 0; i < cellLevels.size(); i++){
            for (int j = 0; j < cellLevels.size(); j++){
                for (Cell start : cellLevels.get(i)){
                    for (Cell target : cellLevels.get(j)){
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
                }System.out.printf("From Level %d to Level %d: PathLength: %d, Stored: %d, NextFound: %d\n CellCount: %d\n AveragePathLength: %.4f, AverageCellsStored: %.4f, AverageNextFound: %.4f\n",
                        i, j, totalPathLength, totalStored, totalNextFound, cellCount,
                        totalPathLength/(double)cellCount, totalStored/(double)cellCount, totalNextFound/(double)cellCount);
                totalPathLength = totalStored = totalNextFound = cellCount = 0;
            }
        }
    }
    public static void main(String[] args){
        for (boolean directional : new boolean[]{false, true}){
            experimentFor(new BreadthFirstSearch(directional));
            experimentFor(new RecursiveBreadthFirstSearch(directional));
            experimentFor(new DepthFirstSearch(directional));
            experimentFor(new RecursiveDFS(directional));

            DepthLimitedDFS dLDFSearcher = new DepthLimitedDFS(directional);
            RecursiveDepthLimitedDFS recursiveDLDFSearcher = new RecursiveDepthLimitedDFS(directional);
            for (int max = 1; max < 64; max++){
                dLDFSearcher.depthLimit = recursiveDLDFSearcher.depthLimit = max;
                System.out.printf("maxDepth: %d\n", max);
                experimentFor(dLDFSearcher);
                experimentFor(recursiveDLDFSearcher);
            }
            IterativeDeepeningDFS iterativeDeepeningSearcher = new IterativeDeepeningDFS(directional);
            RecursiveIDDFS recursiveIDDFSearcher = new RecursiveIDDFS(directional);
            for (int gap = 1; gap < 16; gap++){
                iterativeDeepeningSearcher.gap = recursiveIDDFSearcher.gap = gap;
                System.out.printf("Gap: %d\n", gap);
                experimentFor(iterativeDeepeningSearcher);
                experimentFor(recursiveIDDFSearcher);
            }
            for(Boolean euclidean : euclidean){
                experimentFor(new GreedySearch(euclidean, directional));
                experimentFor(new RecursiveGreedySearch(euclidean, directional));
                experimentFor(new ASearch(euclidean, directional));
                experimentFor(new RecursiveASearch(euclidean, directional));
                
                IterativeDeepeningGreedySearch iterativeDeepeningGreedySearcher = new IterativeDeepeningGreedySearch(euclidean, directional);
                RecursiveIterativeDeepeningGreedySearch recursiveIterativeDeepeningGreedySearch = new RecursiveIterativeDeepeningGreedySearch(euclidean, directional);
                IterativeDeepeningASearch iterativeDeepeningASearcher = new IterativeDeepeningASearch(euclidean, directional);
                RecursiveIDASearch recursiveIDASearcher = new RecursiveIDASearch(euclidean, directional);
                BeamSearch beamSearcher = new BeamSearch(euclidean, directional);
                RecursiveBeamSearch recursiveBeamSearcher = new RecursiveBeamSearch(euclidean, directional);
                for (int gap = 1; gap < 32; gap++){
                    iterativeDeepeningGreedySearcher.gap = recursiveIterativeDeepeningGreedySearch.gap 
                    = iterativeDeepeningASearcher.gap = recursiveIDASearcher.gap 
                    = beamSearcher.beamWidth = recursiveBeamSearcher.beamWidth = gap;
                    System.out.printf("Gap: %d\n", gap);
                    experimentFor(iterativeDeepeningGreedySearcher);
                    experimentFor(recursiveIterativeDeepeningGreedySearch);
                    experimentFor(iterativeDeepeningASearcher);
                    experimentFor(recursiveIDASearcher);
                    experimentFor(beamSearcher);
                    experimentFor(recursiveBeamSearcher);
                }
            }
        }
    }
}