package Fundamentals.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 最小生成树的 Prim 算法的延时实现
 */
public class LazyPrimMST {
    private boolean marked[]; // 已标记顶点
    private Queue<Edge> mst; // 最小生成树的边
    private PriorityQueue<Edge> pq; // 横切边（包括失效的边）

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();

        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll(); // 从pq中得到权重最小的边
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) // 跳过失效的边
                continue;
            mst.offer(e); // 将权重最小的边添加到树中

            // 继续访问边的未标记顶点
            if (!marked[v])
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // 标记顶点v并将所有连接v和未被标记顶点的边加入pq
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)])
                pq.offer(e);
    }
}