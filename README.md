# Shortest paths algorithms research

## How to run:

```bash
make clean
make
make run
```

## Structure explanation

- in -> 10 input tests
- out -> output resulted from running the input
- result -> tests from my machine running Ubuntu 20 with cpu I5 8300H and 16 GB
RAM DDR4

## About

Everything was done using Java and JDK15 compiled with javac.

All graphs are oriented and weighted.

Tests include:

- small tests: test0, test1, test2, test3 (negative edges), test 4 (negative
edges)
- large tests: test5, test6, test7, test8 (negative edges), test 9 (negative
edges)

## Mentions

INF - node unreachable

Every algorithm displays an error message in the output tests if it isn't
applicable.

## Algorithms implementation

### Dijkstra:

I decided to implement Dijkstra with Priority Queue and it was implemented on
every vertex of the graph in order to compute the total pairs, with the total
complexity of O(V^2). But Dijkstra was runned for all vertices in order to get
all pairs so the total complexity would be in fact O(V^3).
The tests contain positive and negative edge weights, so Djikstra evaluates
every edge and if it is a negative one, then it displays an error message.


### Floyd-Warshall:

This algorithm runs in O(V^3) due to the three for loops, in which we traverse
each node. Corner cases for this algorithm include negative cycles, in which
the Floyd-Warshall algorithm detects them and displays an error message.
It detects the negative cycle by having a negative number on the diagonal of the
final matrix.


### Johnson's algorithm using Bellman-Ford and Dijkstra with Priority Queue:

Johnson's algorithm combines Bellman-Ford and previous Dijkstra. Bellman-Ford
is used to compute the distances from the new source to all the others, while
Dijkstra is implemented on every vertices.
Corner case for this algorithm is when finding a negative cycle, the algorithm
must stop so it displays an error message.
Complexity for this algorithm is O(V^2 *lgV + V*E).

If we check the timings, it is for sure that Floyd-Warshall is the fastest due
to the graphs being average dense graphs. Then Dijkstra follows but it cannot
interpret negative edge weights, so Johnson is the most versatile here but with
the slowest timing.

### References:

1. Introduction to Algorithms by Thomas Cormen
2. https://acs.curs.pub.ro/2019/course/view.php?id=145 - SD Course last year
3. https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
4. https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
5. https://www.sanfoundry.com/java-program-to-implement-johnsons-algorithm/

Algorithms were modified in order to run properly on all cases and tests.

## Project structure

```bash
├── BellmanFord.class
├── BellmanFord.java
├── DPQNode.class
├── DPQ.class
├── DPQ.java
├── DPQString.class
├── DPQString.java
├── FW.class
├── FW.java
├── FWString.class
├── FWString.java
├── in
│   ├── test0.in
│   ├── test1.in
│   ├── test2.in
│   ├── test3.in
│   ├── test4.in
│   ├── test5.in
│   ├── test6.in
│   ├── test7.in
│   ├── test8.in
│   └── test9.in
├── ioan_razvan.abagiu.zip
├── Johnson.class
├── Johnson.java
├── JohnsonString.class
├── JohnsonString.java
├── Main.class
├── Main.java
├── Makefile
├── out
│   ├── test0.out
│   ├── test1.out
│   ├── test2.out
│   ├── test3.out
│   ├── test4.out
│   ├── test5.out
│   ├── test6.out
│   ├── test7.out
│   ├── test8.out
│   └── test9.out
├── README
├── result
│   ├── test0.out
│   ├── test1.out
│   ├── test2.out
│   ├── test3.out
│   ├── test4.out
│   ├── test5.out
│   ├── test6.out
│   ├── test7.out
│   ├── test8.out
│   └── test9.out
└── ShortestPaths.iml
```

