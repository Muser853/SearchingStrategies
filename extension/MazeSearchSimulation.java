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
        
        experimentFor("DStarSearchEuclid");
        experimentFor("RecursiveDStarSearchEuclid");
        experimentFor("DStarSearchManhattan");
        experimentFor("RecursiveDStarSearchManhattan");
        experimentFor("BidirectionalDStarSearchEuclid");
        experimentFor("RecursiveBidirectionalDStarSearchEuclid");
        experimentFor("BidirectionalDStarSearchManhattan");
        experimentFor("RecursiveBidirectionalDStarSearchManhattan");
        
        experimentFor("IterativeDeepeningASearchEuclid");
        experimentFor("RecursiveIterativeDeepeningASearchEuclid");
        experimentFor("IterativeDeepeningASearchManhattan");
        experimentFor("RecursiveIterativeDeepeningASearchManhattan");
        experimentFor("BidirectionalIterativeDeepeningASearchEuclid");
        experimentFor("RecursiveBidirectionalIterativeDeepeningASearchEuclid");
        experimentFor("BidirectionalIterativeDeepeningASearchManhattan");
        experimentFor("RecursiveBidirectionalIterativeDeepeningASearchManhattan");

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

        experimentFor("IterativeDeepeningSearch");
        experimentFor("BidirectionalIterativeDeepeningSearch");
        experimentFor("IDDFS");
        experimentFor("BidirectionalIDDFS");
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

            case "AStarEuclid" -> new ASearch(Boolean.TRUE, false);
            case "RecursiveAStarEuclid" -> new RecursiveASearch(Boolean.TRUE, false);
            case "AStarSM" -> new ASearch(null, false);
            case "RecursiveAStarSM" -> new RecursiveASearch(null, false);
            case "AStarManhattan" -> new ASearch(Boolean.FALSE, false);
            case "RecursiveAStarManhattan" -> new RecursiveASearch(Boolean.FALSE, false);

            case "BidirectionalAStarEuclid" -> new ASearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalAStarEuclid" -> new RecursiveASearch(Boolean.TRUE, true);
            case "BidirectionalAStarSM" -> new ASearch(null, true);
            case "RecursiveBidirectionalAStarSM" -> new RecursiveASearch(null, true);
            case "BidirectionalAStarManhattan" -> new ASearch(Boolean.FALSE, true);
            case "RecursiveBidirectionalAStarManhattan" -> new RecursiveASearch(Boolean.FALSE, true);

            case "IterativeDeepeningASearchEuclid" -> new IterativeDeepeningASearch(Boolean.TRUE, false);
            case "RecursiveIterativeDeepeningASearchEuclid" -> new RecursiveIDASearch(Boolean.TRUE, false);
            case "IterativeDeepeningASearchSM" -> new IterativeDeepeningASearch(null, false);
            case "RecursiveIterativeDeepeningASearchSM" -> new RecursiveIDASearch(null, false);
            case "IterativeDeepeningASearchManhattan" -> new IterativeDeepeningASearch(Boolean.FALSE, false);
            case "RecursiveIterativeDeepeningASearchManhattan" -> new RecursiveIDASearch(Boolean.FALSE, false);

            case "BidirectionalIterativeDeepeningASearchEuclid" -> new IterativeDeepeningASearch(Boolean.TRUE, true);
            case "RecursiveBidirectionalIterativeDeepeningASearchEuclid" -> new RecursiveIDASearch(Boolean.TRUE, true);
            case "BidirectionalIterativeDeepeningASearchSM" -> new IterativeDeepeningASearch(null, true);
            case "RecursiveBidirectionalIterativeDeepeningASearchSM" -> new RecursiveIDASearch(null, true);
            case "BidirectionalIterativeDeepeningASearchManhattan" -> new IterativeDeepeningASearch(Boolean.FALSE, true);
            case "RecursiveBidirectionalIterativeDeepeningASearchManhattan" -> new RecursiveIDASearch(Boolean.FALSE, true);

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

            case "IterativeDeepeningSearch" -> new IterativeDeepeningSearch(false);
            case "BidirectionalIterativeDeepeningSearch" -> new IterativeDeepeningSearch(true);
            case "IDDFS" -> new IterativeDeepeningDepthFirstSearch(false);
            default -> new IterativeDeepeningDepthFirstSearch(true);
        };
    }
}