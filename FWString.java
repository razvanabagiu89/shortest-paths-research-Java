package algorithms;

import java.io.IOException;

public class FWString {

    public static void build(int V, int[][] mat, long thisMuch, StringBuilder printMe)
            throws IOException {

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (mat[i][i] < 0) {
                    printMe.append("At least one negative cycle detected.").append(
                            " Floyd-Warshall cannot be applied!");
                    return;
                }
            }
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (mat[i][j] == Integer.MAX_VALUE) {
                    printMe.append("INF").append(" ");
                    continue;
                }
                printMe.append(mat[i][j]).append(" ");
            }
            printMe.append("\n");
        }
        printMe.append("\ntime: ").append(thisMuch).append(" ns");
    }
}
