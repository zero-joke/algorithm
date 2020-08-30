package algs4.Graphs;

/**
 * 双色问题。能够用两种颜色将图的所有顶点着色，使得任意一条边的两个端点的颜色都不相同吗?这个问题也等价于:这是一幅二分图吗?
 * 实现技巧：根据双色问题的定义，遍历顶点，将相邻顶点标记为相反的颜色
 */
public class TwoColor {
    private boolean marked[];
    private boolean color[];
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s])
                dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];   // 将相邻顶点标记为相反的颜色
                dfs(G, w);
            } else if (color[w] == color[v])
                isTwoColorable = false;
        }
    }

    public boolean isTwoColorable() {
        return isTwoColorable;
    }
}