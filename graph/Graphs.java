package graph;

/**
 * 无向图常用处理方法
 */
public class Graphs {
    // 计算v的度数（度数：v相连的顶点数）
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v)) {
            ++degree;
        }
        return degree;
    }

    // 计算所有顶点的最大度数
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++)
            if (degree(G, v) > max)
                max = degree(G, v);
        return max;
    }

    // 计算所有顶点的平均度数
    public static double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    // 计算自环的个数
    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
                if (v == w)
                    count++;
        return count / 2; // 每条边都被记过两次
    }
}