import java.util.*;

public class DistanceVectorRouting {
    static final int INF = 999; // Infinity cost

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of routers: ");
        int n = sc.nextInt();

        int[][] cost = new int[n][n];
        int[][] dist = new int[n][n];
        int[][] nextHop = new int[n][n];

        System.out.println("\nEnter the cost matrix (use 999 for no direct link):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                dist[i][j] = cost[i][j];

                if (cost[i][j] != INF && i != j)
                    nextHop[i][j] = j;
                else
                    nextHop[i][j] = -1;
            }
        }

        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (dist[i][j] > cost[i][k] + dist[k][j]) {
                            dist[i][j] = cost[i][k] + dist[k][j];
                            nextHop[i][j] = k;
                            updated = true;
                        }
                    }
                }
            }
        } while (updated);

        System.out.println("\n--- Routing Tables ---");
        for (int i = 0; i < n; i++) {
            System.out.println("\nRouter " + (i + 1) + " Table:");
            System.out.println("Destination\tNext Hop\tDistance");
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                System.out.println((j + 1) + "\t\t" +
                    (nextHop[i][j] != -1 ? (nextHop[i][j] + 1) : "-") +
                    "\t\t" + dist[i][j]);
            }
        }

        sc.close();
    }
}
