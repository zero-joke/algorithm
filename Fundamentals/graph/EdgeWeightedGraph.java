package Fundamentals.graph;

import Fundamentals.base.Bag;

/**
 * 加权无向图的数据类型
 */
public class EdgeWeightedGraph {
    private final int V; // 顶点总数
    private int E; // 边数
    private Bag<Edge> adj[]; // 邻接表

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<Edge>();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        adj[v].add(e);
        adj[e.other(v)].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
}