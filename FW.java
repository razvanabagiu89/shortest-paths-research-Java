package algorithms;

import java.io.FileNotFoundException;

public class FW {

    public static int[][] FW(int V, int[][] dist) throws FileNotFoundException {

        // loop through all nodes
        for (int k = 0; k < V; k++) {
            // mark the node as source
            for (int i = 0; i < V; i++) {
                // loop through all nodes as destinations
                for (int j = 0; j < V; j++) {

                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }
}
