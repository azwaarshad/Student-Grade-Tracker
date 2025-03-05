package mypackage;


import java.util.*;

public class GraphDFS {
    private int vertices;
    private LinkedList<Integer> adjList[];

    // Constructor
    public GraphDFS(int v) {
        vertices = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Method to add an edge to the graph
    void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // DFS traversal from a given source
    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adjList[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // DFS traversal of the vertices reachable from v
    void DFS(int v) {
        boolean visited[] = new boolean[vertices];
        DFSUtil(v, visited);
    }

    // Main method to test the DFS implementation
    public static void main(String args[]) {
        GraphDFS g = new GraphDFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("DFS traversal starting from vertex 2:");
        g.DFS(2);
    }
}
