import java.util.*;

public class Experiment {
    public void runTraversals(Graph g, int startId) {
        System.out.println("\n--- Traversal on graph with " + g.getAdjList().size() + " vertices ---");
        long startBfs = System.nanoTime();
        g.bfs(startId);
        long endBfs = System.nanoTime();
        long bfsTime = endBfs - startBfs;
        long startDfs = System.nanoTime();
        g.dfs(startId);
        long endDfs = System.nanoTime();
        long dfsTime = endDfs - startDfs;

        System.out.printf("BFS time: %d ns\n", bfsTime);
        System.out.printf("DFS time: %d ns\n", dfsTime);
    }
    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        for (int size : sizes) {
            Graph g = createConnectedGraph(size);
            g.printGraph();
            runTraversals(g, 0);
        }
    }
    private Graph createConnectedGraph(int n) {
        Graph graph = new Graph(false);
        for (int i = 0; i < n; i++) {
            graph.addVertex(new Vertex(i));
        }
        for (int i = 0; i < n; i++) {
            if (i + 1 < n) graph.addEdge(i, i + 1);
            if (i + 2 < n) graph.addEdge(i, i + 2);
        }
        if (n > 5) {
            graph.addEdge(0, n/2);
            graph.addEdge(1, n-1);
        }
        return graph;
    }
    public void printResults() {
        System.out.println("\n===== Performance Summary =====");
        System.out.println("| V | BFS time (ns) | DFS time (ns) |");
        System.out.println("|---|---------------|---------------|");
        System.out.println("(See detailed output above)");
    }
}
