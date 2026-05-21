import java.util.*;

public class Graph {
    private final Map<Integer, List<Edge>> adjList;
    private final List<Edge> edges;
    private final Map<Integer, Vertex> vertices;

    public Graph() {
        adjList = new HashMap<>();
        edges = new ArrayList<>();
        vertices = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        int id = v.getId();
        if (!vertices.containsKey(id)) {
            vertices.put(id, v);
            adjList.put(id, new ArrayList<>());
        }
    }
    public void addEdge(int fromId, int toId) {
        addWeightedEdge(fromId, toId, 1);
    }
    public void addWeightedEdge(int fromId, int toId, int weight) {
        Vertex from = vertices.get(fromId);
        Vertex to = vertices.get(toId);
        if (from == null || to == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        Edge edge1 = new Edge(from, to, weight);
        Edge edge2 = new Edge(to, from, weight);
        adjList.get(fromId).add(edge1);
        adjList.get(toId).add(edge2);
        edges.add(edge1);
        edges.add(edge2);
    }

    public void printGraph() {
        System.out.println("Adjacency List (with weights):");
        for (int id : adjList.keySet()) {
            System.out.print(vertices.get(id) + " -> ");
            for (Edge e : adjList.get(id)) {
                System.out.print(e.getDestination() + "(" + e.getWeight() + ") ");
            }
            System.out.println();
        }
    }
    public List<Vertex> bfs(int startId) {
        if (!vertices.containsKey(startId)) return Collections.emptyList();
        List<Vertex> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(startId);
        queue.add(startId);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(vertices.get(current));
            for (Edge edge : adjList.get(current)) {
                int neighbor = edge.getDestination().getId();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return order;
    }
    public List<Vertex> dfs(int startId) {
        if (!vertices.containsKey(startId)) return Collections.emptyList();
        List<Vertex> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(startId, visited, order);
        return order;
    }

    private void dfsRecursive(int currentId, Set<Integer> visited, List<Vertex> order) {
        visited.add(currentId);
        order.add(vertices.get(currentId));
        for (Edge edge : adjList.get(currentId)) {
            int neighbor = edge.getDestination().getId();
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, order);
            }
        }
    }

    public int getVertexCount() {
        return vertices.size();
    }
    public int getEdgeCount() {
        return edges.size() / 2;
    }
    public void dijkstra(int startId) {
        if (!vertices.containsKey(startId)) {
            System.out.println("Start vertex not found.");
            return;
        }

        int n = vertices.size();
        Map<Integer, Integer> idToIndex = new HashMap<>();
        int idx = 0;
        for (int id : vertices.keySet()) {
            idToIndex.put(id, idx++);
        }

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int startIdx = idToIndex.get(startId);
        dist[startIdx] = 0;
        for (int i = 0; i < n; i++) {
            int uIdx = -1;
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    uIdx = j;
                }
            }
            if (uIdx == -1) break;

            visited[uIdx] = true;
            int uId = getVertexIdByIndex(uIdx, idToIndex);
            for (Edge edge : adjList.get(uId)) {
                int vId = edge.getDestination().getId();
                int vIdx = idToIndex.get(vId);
                int weight = edge.getWeight();
                if (!visited[vIdx] && dist[uIdx] != Integer.MAX_VALUE
                        && dist[uIdx] + weight < dist[vIdx]) {
                    dist[vIdx] = dist[uIdx] + weight;
                }
            }
        }
        System.out.println("\n=== Dijkstra's Shortest Path from " + vertices.get(startId) + " ===");
        System.out.println("Vertex\tDistance from Start");
        for (int id : vertices.keySet()) {
            int d = dist[idToIndex.get(id)];
            String distStr = (d == Integer.MAX_VALUE) ? "∞" : String.valueOf(d);
            System.out.println(vertices.get(id) + "\t\t" + distStr);
        }
    }
    private int getVertexIdByIndex(int index, Map<Integer, Integer> idToIndex) {
        for (Map.Entry<Integer, Integer> entry : idToIndex.entrySet()) {
            if (entry.getValue() == index) return entry.getKey();
        }
        return -1;
    }
}
