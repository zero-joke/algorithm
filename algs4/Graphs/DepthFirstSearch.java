package algs4.Graphs;

import java.util.Stack;

/**
 * 深度优先搜算
 * 
 * 实现技巧：根据深度优先搜算的定义，使用回溯
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo; // 从起点到一个顶点的深度搜索路径上的最后一个顶点
    private final int s; // 起点
    private int count;

    public DepthFirstSearch(Graph G, int v) {
        s = v;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        edgeTo[s] = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

    // 起点 到 w 的路径，如果不存在则返回 null
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