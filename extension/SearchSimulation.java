import java.util.ArrayList;

public class SearchSimulation{
    private static int totalPathLength = 0;
    private static int totalStored = 0;
    private static int totalNextFound = 0;
    private static int cellCount = 0;
    private static final Boolean[] euclidean = {Boolean.FALSE, null, Boolean.TRUE};
    private static final boolean[] trueFalse = {false, true};
    private static final ArrayList<LinkedList<Cell>> cellLevels = Cell.generateCellLevels();

    static void experimentFor(AbstractSearch searcher){
        System.out.printf("\n%s %s %s\n", searcher.bidirectional ?
        "bidirectional":"unidirectional", searcher.recursive ?
         "recursive" : "non-recursive", searcher.getClass().getSimpleName());//CSV Header
        
        for (int i = 0; i < cellLevels.size(); i++)for (int j = 0; j < cellLevels.size(); j++){
            for (Cell start : cellLevels.get(i))for (Cell target : cellLevels.get(j)){
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
                System.out.printf("\nFrom Level %d to Level %d: PathLength: %d, Stored: %d, NextFound: %d\n CellCount: %d\n AveragePathLength: %.4f, AverageCellsStored: %.4f, AverageNextFound: %.4f\n",
                    i, j, totalPathLength, totalStored, totalNextFound, cellCount,
                    totalPathLength/(double)cellCount, totalStored/(double)cellCount, totalNextFound/(double)cellCount);
                    
                totalPathLength = totalStored = totalNextFound = cellCount = 0;
            }
        }
    }
    public static void main(String[] args){
        for (boolean directional : trueFalse) for (boolean recursive : trueFalse){
            experimentFor(new BreadthFirstSearch(directional, recursive));
            experimentFor(new DepthFirstSearch(directional, recursive));

            IterativeDeepeningDFS iterativeDeepeningSearcher = new IterativeDeepeningDFS(directional, recursive);
            DepthLimitedDFS dLDFSearcher = new DepthLimitedDFS(directional, recursive);
            for (int max = 1; max < 32; max++){
                dLDFSearcher.depthLimit = iterativeDeepeningSearcher.gap = max;
                System.out.printf("\nmaxDepth: %d\n", max);
                experimentFor(dLDFSearcher);
                experimentFor(iterativeDeepeningSearcher);
            }
            for(Boolean euclidean : euclidean){
                experimentFor(new GreedySearch(euclidean, directional, recursive));
                experimentFor(new ASearch(euclidean, directional, recursive));
                
                IterativeDeepeningGreedySearch iterativeDeepeningGreedySearcher = new IterativeDeepeningGreedySearch(euclidean, directional, recursive);
                IterativeDeepeningASearch iterativeDeepeningASearcher = new IterativeDeepeningASearch(euclidean, directional, recursive);
                BeamSearch beamSearcher = new BeamSearch(euclidean, directional, recursive);
                for (int gap = 1; gap < 32; gap++){
                    iterativeDeepeningGreedySearcher.gap = iterativeDeepeningASearcher.gap = beamSearcher.beamWidth = gap;
                    System.out.printf("\nGap: %d\n", gap);
                    experimentFor(iterativeDeepeningGreedySearcher);
                    experimentFor(iterativeDeepeningASearcher);
                    experimentFor(beamSearcher);
                }
            }
        }
    }
}