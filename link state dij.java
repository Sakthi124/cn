import java.util.*;

public class LinkStateRouting {
    static final int INF = 9999; 

    // Dijkstra Algorithm
    public static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n];         
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];        

        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);
        dist[source] = 0;
        parent[source] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        printSolution(dist, parent, source);
    }

    // Find vertex with minimum distance
    public static int minDistance(int[] dist, boolean[] visited) {
        int min = INF, index = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                index = v;
            }
        }
        return index;
    }

    // Display output
    public static void printSolution(int[] dist, int[] parent, int source) {
        System.out.println("\nRouter\tPath\t\tCost");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < dist.length; i++) {
            if (i != source) {
                System.out.print(source + " -> " + i + "\t");
                printPath(parent, i);
                System.out.println("\t\t" + dist[i]);
            }
        }
    }

    // Recursively print path from source to destination
    public static void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(j);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print(" -> " + j);
    }

    // MAIN FUNCTION
    public static void main(String[] args) {
        // Adjacency Matrix Representation
        int[][] graph = {
            //A  B  C  D  E  F
            {0, 4, 0, 0, 0, 0}, // A
            {4, 0, 3, 0,10, 0}, // B
            {0, 3, 0, 4, 1, 0}, // C
            {0, 0, 4, 0, 4, 0}, // D
            {0,10, 1, 4, 0, 1}, // E
            {0, 0, 0, 0, 1, 0}  // F
        };

        int source = 5; // Start from Router F (index 5)
        dijkstra(graph, source);
    }
}
