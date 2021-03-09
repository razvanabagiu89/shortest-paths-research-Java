package algorithms;

import java.util.*;

public class DPQ {

    // store distances
    public int[] dist;
    // to check if node is visited
    private Set<Integer> settled;
    // priority queue
    private PriorityQueue<Node> pq;
    // vertices
    private int V;
    // adjacency list
    List<List<Node>> adj;

    public DPQ(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    public void dijkstra(List<List<Node>> adj, int src) {
        this.adj = adj;
        settled.clear(); // reset

        // initialize INF
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // source node
        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (pq.size() != 0) {

            // remove from pq
            int u = pq.remove().node;
            // mark it as visited
            settled.add(u);
            // check all neighbours
            e_Neighbours(u);
        }
    }

    private void e_Neighbours(int u) {

        int edgeDistance = -1;
        int newDistance = -1;

        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                if (newDistance < dist[v.node]) {
                    dist[v.node] = newDistance;
                    // add new node in pq
                    pq.add(new Node(v.node, dist[v.node]));
                }
            }
        }
    }

    public static class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.cost < node2.cost)
                return -1;
            if (node1.cost > node2.cost)
                return 1;
            return 0;
        }
    }
}
