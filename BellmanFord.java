package algorithms;

public class BellmanFord {

    private int[] distances;
    private int V;

    public BellmanFord(int V) {

        this.V = V;
        distances = new int[V];
    }

    public void BellmanFordEvaluation(int source, int[][] adjacencymatrix) {

        for (int node = 0; node < V; node++) {
            distances[node] = Integer.MAX_VALUE;
        }

        distances[source] = 0;
        for (int sourcenode = 0; sourcenode < V; sourcenode++) {
            for (int destinationnode = 0; destinationnode < V; destinationnode++) {

                if (adjacencymatrix[sourcenode][destinationnode] != Integer.MAX_VALUE) {
                    if (distances[sourcenode] != Integer.MAX_VALUE) {
                        if (distances[destinationnode] > distances[sourcenode]
                                + adjacencymatrix[sourcenode][destinationnode]) {

                            distances[destinationnode] = distances[sourcenode]
                                    + adjacencymatrix[sourcenode][destinationnode];
                        }
                    }
                }
            }
        }
    }

    public int[] getDistances() {
        return distances;
    }
}
