Name: Aruzhan Rsaliyeva

Group: IT-2502

Assignment 4: Graph Traversal and Representation System

A. Project Overview

This project implements a graph data structure using an adjacency list representation.  
A graph consists of vertices (nodes) and edges (connections). Two fundamental traversal algorithms are implemented:

- Breadth-First Search (BFS) – explores neighbours level by level.
- Depth-First Search (DFS) – explores as far as possible along each branch before backtracking.

The program builds graphs of different sizes (10, 30, 100 vertices), runs both traversals, measures execution time, and prints the traversal order.

B. Class Descriptions

Vertex- Represents a graph node with a unique integer ID. 

Edge- Represents a directed connection from source to destination vertex. 

Graph- Maintains a map of vertices and an adjacency list (Map<Integer, List<Integer>>). Supports adding vertices/edges, printing the graph, and BFS/DFS traversals.

Experiment- Creates test graphs, runs traversals, measures time using System.nanoTime(), and prints results

Main- Entry point – creates an Experiment object and runs multiple tests.

The adjacency list is chosen for efficiency:  

- Space: O(V + E)
  
- Neighbour iteration: O(degree(v))

  C. Algorithm Descriptions

  BFS:

1. Mark the start vertex as visited and enqueue it.

3. While queue is not empty:
   
   - Dequeue a vertex u.
     
   - Visit u (print its ID).
     
   - For every neighbour v of u, if v is not visited, mark visited and enqueue v.

Use cases: Shortest path (unweighted graphs), web crawling, social network “friend suggestions”.

Time complexity: O(V + E) – each vertex and edge is processed once.

DFS:

1. Mark the current vertex as visited.
   
2. Visit the vertex.
   
3. Recursively visit all unvisited neighbours.

Use cases:Topological sorting, detecting cycles, solving mazes, finding connected components.

Time complexity: O(V + E) – same as BFS

D. Experimental Results

Tests were run on a connected undirected graph where each vertex connects to the next two vertices (plus a couple of extra edges). Traversal starts at vertex 0.

| Vertices | BFS time (ns) | DFS time (ns) |
|----------|---------------|---------------|
| 10       | 48500         | 37200         |
| 30       | 126800        | 108300        |
| 100      | 489200        | 452100        |

Traversal order (10 vertices):

- BFS: V0 V1 V2 V5 V3 V4 V6 V7 V8 V9
  
- DFS: V0 V1 V2 V4 V5 V3 V6 V8 V9 V7





