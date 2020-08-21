package graph;

import base.Bag;

/**
 * 加权有向图
 */
public class EdgeWeightedDiGraph {
    private final int V;    // 顶点总数
    private int E;  //边的总数
    private Bag<DirectedEdge>[] adj;    // 邻接表

    public EdgeWeightedDiGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for(int v=0; v<V;++v) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for(Bag<DirectedEdge> item: adj) {
            for(DirectedEdge edge: item) {
                bag.add(edge);
            }
        }
        return bag;
    }
}