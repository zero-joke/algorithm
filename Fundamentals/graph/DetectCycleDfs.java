package Fundamentals.graph;

/**
 * 使用dfs检测图是否有环
 */
public class DetectCycleDfs {
    private boolean hasCycle;
    private boolean marked[];

    public DetectCycleDfs(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            // 遍历所有定点，如果已经标记过了就不用dfs搜索，所以是搜索每个独立的联通分量
            if (!marked[s])
                dfs(G, s, s);
        }
    }

    // 顶点u为从顶点s深度优先搜索到v的路径上的最后一个顶点
    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w, v);
            else if (w != u)
                // 如果w已经标记过，判断w是否是从s深度优先搜索到v的路径上的最后一个顶点，
                // 如果不是则说明s可以从其他路径到达w，则图中一定存在环
                hasCycle = true;
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}