package Fundamentals.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先搜素
 * 
 * 实现技巧：据深度优先搜算的定义，借助队列
 */
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo; // 从起点到一个顶点的深度搜索路径上的最后一个顶点
    private final int s; // 起点

    public BreadthFirstPaths(Graph G, int v) {
        s = v;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        edgeTo[s] = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true; // 标记起点
        queue.offer(s); // 将它加入队列
        while (!queue.isEmpty()) {
            int v = queue.poll(); // 从队列中删去下一顶点
            for (int w : G.adj(v)) {
                if (!marked[w]) { // 对于每个未被标记的相邻顶点(使用广度优先，如果此时顶底还没有被标记，则当前路径一定是最短的)
                    marked[w] = true; // 更新为已标记
                    edgeTo[w] = v; // 保存最短路径的最后一条边
                    queue.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int w) {
        if (!marked[w])
            return null;
        Stack<Integer> stack = new Stack<>();
        for (int x = w; x != s; x = edgeTo[x])
            stack.push(x);
        stack.push(s);
        return stack;
    }
}