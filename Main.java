
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(System.getProperty("user.dir"));

        final int testsNo = 10;
        for(int id = 0; id < testsNo; id++) {

            StringBuilder printMe = new StringBuilder();
            String pathIn = System.getProperty("user.dir") + "/in/test" + id + ".in";
            File file = new File(pathIn);
            Scanner scanner = new Scanner(file);

            // DPQ - Dijkstra Priority Queue
            printMe.append("Djikstra with Priority Queue:\n\n");

            // parsing input
            int V = Integer.parseInt(scanner.next());
            scanner.next(); // jump over E
            int[][] mat = new int[V][V];
            // initializing adjacency list for DPQ
            List<List<DPQ.Node>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                List<DPQ.Node> item = new ArrayList<>();
                adj.add(item);
            }
            int from, to, cost;
            while (scanner.hasNext()) {
                from = Integer.parseInt(scanner.next());
                to = Integer.parseInt(scanner.next());
                cost = Integer.parseInt(scanner.next());

                adj.get(from).add(new DPQ.Node(to, cost));
            }

            // run and time on DPQ
            DPQ dpq = new DPQ(V);
            long start = System.nanoTime();
            for (int i = 0; i < V; i++) {

                dpq.dijkstra(adj, i);
                System.arraycopy(dpq.dist, 0, mat[i], 0, V);
            }
            long thisMuch = System.nanoTime() - start;
            // stringify the result
            DPQString.build(V, mat, thisMuch, printMe);


            // Floyd Warshall
            printMe.append("\n\n").append("Floyd Warshall:\n\n");

            // parsing input
            scanner = new Scanner(file);
            scanner.next(); // jump over V - already have it
            scanner.next(); // jump over E

            // initialize graph
            int[][] graph = new int[V][V];
            // set to INF
            for(int i = 0; i < V; i++) {
                Arrays.fill(graph[i], Integer.MAX_VALUE);
                graph[i][i] = 0;
            }
            while (scanner.hasNext()) {
                from = Integer.parseInt(scanner.next());
                to = Integer.parseInt(scanner.next());
                cost = Integer.parseInt(scanner.next());

                graph[from][to] = cost;
            }

            // to store the distances
            int[][] dist = new int[V][V];

            // initialize with adjacency matrix weights
            for (int i = 0; i < V; i++) {
                System.arraycopy(graph[i], 0, dist[i], 0, V);
            }

            // run and time Floyd-Warshall
            start = System.nanoTime();
            dist = FW.FW(V, dist);
            thisMuch = System.nanoTime() - start;
            // stringify the result
            FWString.build(V, dist, thisMuch, printMe);

            // Johnson
            printMe.append("\n\n").append("Johnson:\n\n");

            // parsing input
            scanner = new Scanner(file);
            scanner.next(); // jump over V (we already have it)
            scanner.next(); // jump over E

            //initialize to INF
            for(int i = 0; i < V; i++) {
                Arrays.fill(graph[i], Integer.MAX_VALUE);
                graph[i][i] = 0;
            }
            while (scanner.hasNext()) {
                from = Integer.parseInt(scanner.next());
                to = Integer.parseInt(scanner.next());
                cost = Integer.parseInt(scanner.next());

                graph[from][to] = cost;
            }

            // run and time Johnson
            Johnson johnson = new Johnson(V);
            start = System.nanoTime();
            johnson.Johnsons(graph);
            thisMuch = System.nanoTime() - start;
            // stringify the result
            JohnsonString.build(V, johnson.getMat(), thisMuch, printMe);

            // write current test result to file
            String pathOut = System.getProperty("user.dir") + "/out/test" + id
                    + ".out";
            Path path = Paths.get(pathOut);
            Files.write(path, printMe.toString().getBytes());
        }
    }
}
