package mypackage;


import java.util.*;

public class GraphBFS {
    private int vertices;
    private LinkedList<Integer> adjList[];

    // Constructor
    public GraphBFS(int v) {
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

    // BFS traversal from a given source
    void BFS(int s) {
        boolean visited[] = new boolean[vertices];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            Iterator<Integer> i = adjList[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // Main method to test the BFS implementation
    public static void main(String args[]) {
        GraphBFS g = new GraphBFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("BFS traversal starting from vertex 2:");
        g.BFS(2);
    }
}

