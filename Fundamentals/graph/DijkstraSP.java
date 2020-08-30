package Fundamentals.graph;

import java.util.LinkedList;

/**
 * 最短路径的 Dijkstra 算法
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double distTo[];
    private LinkedList<Integer> linkedList;

    public DijkstraSP(EdgeWeightedDiGraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        linkedList = new LinkedList<>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        linkedList.addLast(s);
        while (!linkedList.isEmpty()) {
            relax(G, linkedList.removeFirst());
        }
    }

    private void relax(EdgeWeightedDiGraph G, int v) {
        for (DirectedEdge edge : G.adj(v)) {
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()) {
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
            }
            if (!linkedList.contains(w)) {
                linkedList.addLast(w);
            }
        }
    }
}