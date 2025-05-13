public final class SearchSimulation{
    private static int totalPathLength;
    private static int totalStored;
    private static int totalNextFound;
    private static LinkedList<Cell> path;
    private static final int[] levels = {0, 1, 2, 3, Cell.cellLevels.size()-1};

    public static void main(String[] args) {
        // CSV header
        System.out.println("algorithm, total_path_length, total_cells_stored, total_next_found");
        experimentFor("GreedySearchEuclid");
        experimentFor("RecursiveGreedySearchEuclid");
        experimentFor("GreedySearchManhattan");
        experimentFor("RecursiveGreedySearchManhattan");
        experimentFor("BidirectionalGreedySearchEuclid");
        experimentFor("RecursiveBidirectionalGreedySearchEuclid");
        experimentFor("BidirectionalGreedySearchManhattan");
        experimentFor("RecursiveBidirectionalGreedySearchManhattan");

        experimentFor("AEuclid");
        experimentFor("RecursiveAEuclid");
        experimentFor("AManhattan");
        experimentFor("RecursiveAManhattan");
        experimentFor("BidirectionalAEuclid");
        experimentFor("RecursiveBidirectionalAEuclid");
        experimentFor("BidirectionalAManhattan");
        experimentFor("RecursiveBidirectionalAManhattan");
        
        experimentFor("DSearchEuclid");
        experimentFor("RecursiveDSearchEuclid");
        experimentFor("DSearchManhattan");
        experimentFor("RecursiveDSearchManhattan");
        experimentFor("BidirectionalDSearchEuclid");
        experimentFor("RecursiveBidirectionalDSearchEuclid");
        experimentFor("BidirectionalDSearchManhattan");
        experimentFor("RecursiveBidirectionalDSearchManhattan");
        
        experimentFor("IDASearchEuclid");
        experimentFor("RecursiveIDASearchEuclid");
        experimentFor("IDASearchManhattan");
        experimentFor("RecursiveIDASearchManhattan");
        experimentFor("BidirectionalIDASearchEuclid");
        experimentFor("RecursiveBidirectionalIDASearchEuclid");
        experimentFor("BidirectionalIDASearchManhattan");
        experimentFor("RecursiveBidirectionalIDASearchManhattan");

        experimentFor("BFS");
        experimentFor("RecursiveBFS");
        experimentFor("BidirectionalBFS");
        experimentFor("RecursiveBidirectionalBFS");

        experimentFor("BeamSearch");
        experimentFor("RecursiveBeamSearch");
        experimentFor("BidirectionalBeamSearch");
        experimentFor("RecursiveBidirectionalBeamSearch");
        
        experimentFor("DFS");
        experimentFor("RecursiveDFS");
        experimentFor("BidirectionalDFS");
        experimentFor("RecursiveBidirectionalDFS");

        experimentFor("IDDFS");
        experimentFor("RecursiveIDDFS");
        experimentFor("BidirectionalIDDFS");
        experimentFor("RecursiveBidirectionalIDDFS");
    }
    static final void experimentFor(String algorithm) {
        AbstractSearch searcher = createSearcher(algorithm);
        for (int i : levels) for(int j : levels)
            for(Cell start : Cell.cellLevels.get(i)) for (Cell target : Cell.cellLevels.get(j)) {
                if (start != target){
                    for(Cell cell : searcher.explored) cell.reset();
                    searcher.explored.clear();
                    searcher.reset();
                    start.g = 1;
                    target.g = -1;
                    totalPathLength += (path = searcher.search(start, target)) == null ? 0 : path.size();
                    totalNextFound += searcher.explored.size();
                    totalStored += searcher.numRemainingCells();
                }
                System.out.printf("%s from level %d to level %d: pathLength: %d, stored: %d, nextFound: %d\n", algorithm, i, j, totalPathLength, totalStored, totalNextFound);
                totalPathLength = totalStored = totalNextFound = 0;
            }
    }
    static final AbstractSearch createSearcher(String type) {
        return switch (type) {
            case "GreedySearchEuclid" -> new GreedySearch(Boolean.TRUE, false);
            case "RecursiveGreedySearchEuclid" -> new RecursiveGreedySearch(Boolean.TRUE, false);
            case "GreedySearchSM" -> new GreedySearch(null, false);
            case "RecursiveGreedySearchSM" -> new RecursiveGreedySearch(null, false);
            case "GreedySearchManhattan" -> new GreedySearch(Boolean.FALSE, false);
            case "RecursiveGreedySearchManhattan" -> new RecursiveGreedySearch(Boolean.FALSE, false);

            case "BidirectionalGreedySearchEuclid" -> new GreedySearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalGreedySearchEuclid" -> new RecursiveGreedySearch(Boolean.TRUE, true);
            case "BidirectionalGreedySearchSM" -> new GreedySearch(null, true);
            case "RecursiveBidirectionalGreedySearchSM" -> new RecursiveGreedySearch(null, true);
            case "BidirectionalGreedySearchManhattan" -> new GreedySearch(Boolean.FALSE, true);
            case "RecursiveBidirectionalGreedySearchManhattan" -> new RecursiveGreedySearch(Boolean.FALSE, true);

            case "AEuclid" -> new ASearch(Boolean.TRUE, false);
            case "RecursiveAEuclid" -> new RecursiveASearch(Boolean.TRUE, false);
            case "ASM" -> new ASearch(null, false);
            case "RecursiveASM" -> new RecursiveASearch(null, false);
            case "AManhattan" -> new ASearch(Boolean.FALSE, false);
            case "RecursiveAManhattan" -> new RecursiveASearch(Boolean.FALSE, false);

            case "BidirectionalAEuclid" -> new ASearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalAEuclid" -> new RecursiveASearch(Boolean.TRUE, true);
            case "BidirectionalASM" -> new ASearch(null, true);
            case "RecursiveBidirectionalASM" -> new RecursiveASearch(null, true);
            case "BidirectionalAManhattan" -> new ASearch(Boolean.FALSE, true);
            case "RecursiveBidirectionalAManhattan" -> new RecursiveASearch(Boolean.FALSE, true);

            case "IDASearchEuclid" -> new IterativeDeepeningASearch(Boolean.TRUE, false);
            case "RecursiveIDASearchEuclid" -> new RecursiveIDASearch(Boolean.TRUE, false);
            case "IDASearchSM" -> new IterativeDeepeningASearch(null, false);
            case "RecursiveIDASearchSM" -> new RecursiveIDASearch(null, false);
            case "IDASearchManhattan" -> new IterativeDeepeningASearch(Boolean.FALSE, false);
            case "RecursiveIDASearchManhattan" -> new RecursiveIDASearch(Boolean.FALSE, false);

            case "BidirectionalIDASearchEuclid" -> new IterativeDeepeningASearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalIDASearchEuclid" -> new RecursiveIDASearch(Boolean.TRUE, true);
            case "BidirectionalIDASearchSM" -> new IterativeDeepeningASearch(null, true);
            case "RecursiveBidirectionalIDASearchSM" -> new RecursiveIDASearch(null, true);
            case "BidirectionalIDASearchManhattan" -> new IterativeDeepeningASearch(Boolean.FALSE, true);
            case "RecursiveBidirectionalIDASearchManhattan" -> new RecursiveIDASearch(Boolean.FALSE, true);

            case "BFS" -> new BreadthFirstSearch(false);
            case "RecursiveBFS" -> new RecursiveBFS(false);
            case "BidirectionalBFS" -> new BreadthFirstSearch(true);
            case "RecursiveBidirectionalBFS" -> new RecursiveBFS(true);

            case "BeamSearch" -> new BeamSearch(Boolean.TRUE, false);
            case "RecursiveBeamSearch" -> new RecursiveBeamSearch(Boolean.TRUE, false);
            case "BidirectionalBeamSearch" -> new BeamSearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalBeamSearch" -> new RecursiveBeamSearch(Boolean.TRUE, true);

            case "DFS" -> new DepthFirstSearch(false);
            case "RecursiveDFS" -> new RecursiveDFS(false);
            case "BidirectionalDFS" -> new DepthFirstSearch(true);
            case "RecursiveBidirectionalDFS" -> new RecursiveDFS(true);

            case "IDDFS" -> new IterativeDeepeningDepthFirstSearch(false);
            case "RecursiveIDDFS" -> new RecursiveIDDFS(false);
            case "BidirectionalIDDFS" -> new IterativeDeepeningDepthFirstSearch(true);
            default -> new RecursiveIDDFS(true);
        };
    }
}