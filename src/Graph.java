import java.util.*;
public class Graph {
    private final Map<Integer, Vertex> vertices;
    private final Map<Integer, List<Integer>> adjList;
    private final List<Edge> edges;
    private boolean directed;
    public Graph(boolean directed) {
        this.directed = directed;
        vertices = new HashMap<>();
        adjList = new HashMap<>();
        edges = new ArrayList<>();
    }
    public void addVertex(Vertex v) {
        int id = v.getId();
        if (!vertices.containsKey(id)) {
            vertices.put(id, v);
            adjList.put(id, new ArrayList<>());
        }
    }
    public void addEdge(int fromId, int toId) {
        if (!vertices.containsKey(fromId) || !vertices.containsKey(toId)) {
            throw new IllegalArgumentException("Vertex not found");
        }
        adjList.get(fromId).add(toId);
        edges.add(new Edge(vertices.get(fromId), vertices.get(toId)));

        if (!directed) {
            adjList.get(toId).add(fromId);
            edges.add(new Edge(vertices.get(toId), vertices.get(fromId)));
        }
    }
    public void printGraph() {
        System.out.println("Graph adjacency list:");
        for (int id : adjList.keySet()) {
            System.out.println(vertices.get(id) + " -> " + adjList.get(id));
        }
    }
    public void bfs(int startId) {
        if (!vertices.containsKey(startId)) {
            System.out.println("Start vertex not found.");
            return;
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(startId);
        queue.add(startId);
        System.out.print("BFS order: ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(vertices.get(current) + " ");
            for (int neighbor : adjList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
    private void dfsUtil(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(vertices.get(current) + " ");
        for (int neighbor : adjList.get(current)) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited);
            }
        }
    }
    public void dfs(int startId) {
        if (!vertices.containsKey(startId)) {
            System.out.println("Start vertex not found.");
            return;
        }
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS order: ");
        dfsUtil(startId, visited);
        System.out.println();
    }
    public Map<Integer, List<Integer>> getAdjList() {
        return adjList;
    }
    public Vertex getVertex(int id) {
        return vertices.get(id);
    }
}
