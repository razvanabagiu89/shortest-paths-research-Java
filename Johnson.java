package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Johnson {

    // source node
    private int SOURCE_NODE;
    // vertices
    private int V;
    // extended matrix with new source
    private int[][] augmentedMatrix;
    // bellman ford converted distances
    private int[] potential;
    // bellman ford algorithm
    private BellmanFord bellmanFord;
    // djikstra priority queue algorithm
    private DPQ dpq;
    // final matrix
    private int[][] mat;

    public Johnson(int V) {
        this.V = V;
        augmentedMatrix = new int[V + 1][V + 1];
        SOURCE_NODE = V;
        potential = new int[V + 1];
        bellmanFord = new BellmanFord(V + 1);
        dpq = new DPQ(V);
        mat = new int[V][V];
    }

    public void Johnsons(int[][] adjacencyMatrix) {

        computeAugmentedGraph(adjacencyMatrix);
        bellmanFord.BellmanFordEvaluation(SOURCE_NODE, augmentedMatrix);
        potential = bellmanFord.getDistances();

        int[][] reweightedGraph = reweightGraph(adjacencyMatrix);
        List<List<DPQ.Node>> adj = matrixToAdjList(reweightedGraph, V);

        for (int source = 0; source < V; source++) {

            dpq.dijkstra(adj, source);
            int[] result = dpq.dist;

            for (int destination = 0; destination < V; destination++) {

                mat[source][destination] = result[destination]
                        + potential[destination] - potential[source];
            }
        }
    }

    // convert matrix to adjacency list in order to use DPQ
    public List<List<DPQ.Node>> matrixToAdjList(int[][] mat, int V) {

        List<List<DPQ.Node>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            List<DPQ.Node> item = new ArrayList<>();
            adj.add(item);
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (mat[i][j] == Integer.MAX_VALUE)
                    continue;
                adj.get(i).add(new DPQ.Node(j, mat[i][j]));
            }
        }
        return adj;
    }

    // compute extended matrix
    private void computeAugmentedGraph(int[][] adjacencyMatrix) {
        
        for (int source = 0; source < V; source++) {
            for (int destination = 0; destination < V; destination++) {
                augmentedMatrix[source][destination] = adjacencyMatrix[source][destination];
            }
        }

        for (int destination = 0; destination < V; destination++) {
            augmentedMatrix[SOURCE_NODE][destination] = 0;
        }
    }
    // reweighting 
    private int[][] reweightGraph(int[][] adjacencyMatrix) {

        int[][] result = new int[V][V];

        for (int source = 0; source < V; source++) {
            for (int destination = 0; destination < V; destination++) {
                result[source][destination] = adjacencyMatrix[source][destination]
                        + potential[source] - potential[destination];
            }
        }
        return result;
    }

    // get final matrix
    public int[][] getMat() {
        return mat;
    }
}