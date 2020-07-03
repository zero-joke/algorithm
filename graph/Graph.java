package graph;

import java.util.Iterator;

import base.Bag;

/**
 * 数据结构：无向图
 */
public class Graph {
    private int V; // 顶点数
    private int E; // 边数
    private Bag<Integer> adj[]; // 记录各个顶点相邻的顶点

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // 向图中添加一条边 v-w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        ++E;
    }

    // 返回和v相连的所有顶点
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}