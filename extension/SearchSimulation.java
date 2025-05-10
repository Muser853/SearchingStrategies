import java.util.Collection;

public final class SearchSimulation{
    static int totalPathLength, totalCellsExplored;
    static Collection<Cell> path;

    public static void main(String[] args) {
        // CSV header
        System.out.println("algorithm, total_path_length, total_cells_explored");
        experimentFor("GreedySearchEuclid");
        experimentFor("RecursiveGreedySearchEuclid");
        experimentFor("GreedySearchManhattan");
        experimentFor("RecursiveGreedySearchManhattan");
        experimentFor("BidirectionalGreedySearchEuclid");
        experimentFor("RecursiveBidirectionalGreedySearchEuclid");
        experimentFor("BidirectionalGreedySearchManhattan");
        experimentFor("RecursiveBidirectionalGreedySearchManhattan");

        experimentFor("AStarEuclid");
        experimentFor("RecursiveAStarEuclid");
        experimentFor("AStarManhattan");
        experimentFor("RecursiveAStarManhattan");
        experimentFor("BidirectionalAStarEuclid");
        experimentFor("RecursiveBidirectionalAStarEuclid");
        experimentFor("BidirectionalAStarManhattan");
        experimentFor("RecursiveBidirectionalAStarManhattan");
        
        experimentFor("IterativeDeepeningASearchEuclid");
        experimentFor("RecursiveIDASearchEuclid");
        experimentFor("IterativeDeepeningASearchManhattan");
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

        experimentFor("IterativeDeepeningDepthFirstSearch");
        experimentFor("RecursiveIDDFS");
        experimentFor("BidirectionalIDDFS");
        experimentFor("RecursiveBidirectionalIDDFS");
    }
    static void experimentFor(String algorithm) {
        AbstractMazeSearch searcher = createSearcher(algorithm);
        totalPathLength = totalCellsExplored = 0;

        for (Cell start : Cell.ends) for (Cell target : Cell.ends) {
            totalPathLength += (path=searcher.search(start, target))==null ? 0: path.size();
            totalCellsExplored += searcher.getCellsExplored();
        }
        System.out.printf("%s Within the ends: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
        totalPathLength = totalCellsExplored = 0;

        for(Cell target: Cell.ends){
             totalPathLength += (path=searcher.search(Cell.origin, target))==null ? 0: path.size();
             totalCellsExplored += searcher.getCellsExplored();
        }
        System.out.printf("%s From origin to ends: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
        totalCellsExplored = totalPathLength = 0;

        for (Cell start : Cell.origin.neighbors)for (Cell target : Cell.ends) {
            totalPathLength += (path=searcher.search(start, target))==null ? 0: path.size();
            totalCellsExplored += searcher.getCellsExplored();
        }
        System.out.printf("%s From 6 heads to ends: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
        totalCellsExplored = totalPathLength = 0;

        for(Cell start: Cell.heads0) for(Cell target: Cell.ends){
            totalPathLength += (path=searcher.search(start, target)) == null ? 0 : path.size();
            totalCellsExplored += searcher.getCellsExplored();
        }
        System.out.printf("%s From 27 heads to ends: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
        totalPathLength = totalCellsExplored = 0;

        for(Cell start: Cell.heads1)for(Cell target: Cell.ends){
            totalPathLength += (path=searcher.search(start, target))==null ? 0: path.size();
            totalCellsExplored += searcher.getCellsExplored();
        }
        System.out.printf("%s From 120 heads to ends: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
        totalPathLength = totalCellsExplored = 0;

        for(Cell start: Cell.heads2) for(Cell target: Cell.ends){
            totalPathLength += (path=searcher.search(start, target))==null ? 0: path.size();
            totalCellsExplored += searcher.getCellsExplored();
        }
        System.out.printf("%s From 534 heads to ends: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
        totalPathLength = totalCellsExplored = 0;

        if (!searcher.bidirectional){
            for(Cell start: Cell.ends){
                totalPathLength += (path=searcher.search(start, Cell.origin))==null ? 0: path.size();
                totalCellsExplored += searcher.getCellsExplored();
            }
            System.out.printf("%s: From ends to origin: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
            totalPathLength = totalCellsExplored = 0;

            for (Cell start : Cell.ends) for (Cell target : Cell.origin.neighbors){
                totalPathLength += (path=searcher.search(start, target))==null ? 0: path.size();
                totalCellsExplored += searcher.getCellsExplored();
            }
            System.out.printf("%s: From ends to 6 heads: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
            totalPathLength = totalCellsExplored = 0;

            for(Cell start: Cell.ends) for (Cell target: Cell.heads0){
                totalPathLength += (path=searcher.search(start, target))==null ? 0: path.size();
                totalCellsExplored += searcher.getCellsExplored();
            }
            System.out.printf("%s: From ends to 27 heads: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
            totalPathLength = totalCellsExplored = 0;

            for(Cell start: Cell.ends) for (Cell target : Cell.heads1){
                totalPathLength += (path = searcher.search(start, target)) == null ? 0 : path.size();
                totalCellsExplored += searcher.getCellsExplored();
            }
            System.out.printf("%s: From ends to 120 heads: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
            totalPathLength = totalCellsExplored = 0;

            for(Cell start: Cell.ends) for(Cell target: Cell.heads2){
                totalPathLength += (path=searcher.search(start, target))==null ? 0: path.size();
                totalCellsExplored += searcher.getCellsExplored();
            }
            System.out.printf("%s: From ends to 534 heads: , %d, %d\n", algorithm, totalPathLength, totalCellsExplored);
            totalPathLength = totalCellsExplored = 0;
        }
    }
    static AbstractMazeSearch createSearcher(String type) {
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

            case "ASearchEuclid" -> new ASearch(Boolean.TRUE, false);
            case "RecursiveASearchEuclid" -> new RecursiveASearch(Boolean.TRUE, false);
            case "ASearchSM" -> new ASearch(null, false);
            case "RecursiveASearchSM" -> new RecursiveASearch(null, false);
            case "ASearchManhattan" -> new ASearch(Boolean.FALSE, false);
            case "RecursiveASearchManhattan" -> new RecursiveASearch(Boolean.FALSE, false);

            case "BidirectionalASearchEuclid" -> new ASearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalASearchEuclid" -> new RecursiveASearch(Boolean.TRUE, true);
            case "BidirectionalASearchSM" -> new ASearch(null, true);
            case "RecursiveBidirectionalASearchSM" -> new RecursiveASearch(null, true);
            case "BidirectionalASearchManhattan" -> new ASearch(Boolean.FALSE, true);
            case "RecursiveBidirectionalASearchManhattan" -> new RecursiveASearch(Boolean.FALSE, true);

            case "IterativeDeepeningASearchEuclid" -> new IterativeDeepeningASearch(Boolean.TRUE, false);
            case "RecursiveIDASearchEuclid" -> new RecursiveIDASearch(Boolean.TRUE, false);
            case "IterativeDeepeningASearchSM" -> new IterativeDeepeningASearch(null, false);
            case "RecursiveIDASearchSM" -> new RecursiveIDASearch(null, false);
            case "IterativeDeepeningASearchManhattan" -> new IterativeDeepeningASearch(Boolean.FALSE, false);
            case "RecursiveIDASearchManhattan" -> new RecursiveIDASearch(Boolean.FALSE, false);

            case "BidirectionalIDASearchEuclid" -> new IterativeDeepeningASearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalIDASearchEuclid" -> new RecursiveIDASearch(Boolean.TRUE, true);
            case "BidirectionalIDASearchSM" -> new IterativeDeepeningASearch(null, true);
            case "RecursiveBidirectionalIDASearchSM" -> new RecursiveIDASearch(null, true);
            case "BidirectionalIDASearchManhattan" -> new IterativeDeepeningASearch(Boolean.FALSE, true);
            case "RecursiveBidirectionalIDASearchManhattan" -> new RecursiveIDASearch(Boolean.FALSE, true);

            case "BFS" -> new MazeBreadthFirstSearch(false);
            case "BidirectionalBFS" -> new MazeBreadthFirstSearch(true);
            case "RecursiveBFS" -> new RecursiveBFS(false);
            case "RecursiveBidirectionalBFS" -> new RecursiveBFS(true);

            case "BeamSearch" -> new BeamSearch(Boolean.TRUE, false);
            case "BidirectionalBeamSearch" -> new BeamSearch(Boolean.TRUE, true);
            case "RecursiveBeamSearch" -> new RecursiveBeamSearch(Boolean.TRUE, false);
            case "RecursiveBidirectionalBeamSearch" -> new RecursiveBeamSearch(Boolean.TRUE, true);

            case "DFS" -> new MazeDepthFirstSearch(false);
            case "BidirectionalDFS" -> new MazeDepthFirstSearch(true);
            case "RecursiveDFS" -> new RecursiveDFS(false);
            case "RecursiveBidirectionalDFS" -> new RecursiveDFS(true);

            case "IDDFS" -> new IterativeDeepeningDepthFirstSearch(false);
            case "RecursiveIDDFS" -> new RecursiveIDDFS(false);
            case "BidirectionalIDDFS" -> new IterativeDeepeningDepthFirstSearch(true);
            case "RecursiveBidirectionalIDDFS" -> new RecursiveIDDFS(true);
            default -> new IterativeDeepeningDepthFirstSearch(true);
        };
    }
}