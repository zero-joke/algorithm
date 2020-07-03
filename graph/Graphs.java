package graph;

/**
 * 无向图常用处理方法
 */
public class Graphs {
    // 计算v的度数（度数：v相连的顶点数）
    public static int degree(Graph G, int v) {
        int degree = 0;
        for(int w : G.adj(v)) {
            ++degree;
        }
        return degree;
    }
}