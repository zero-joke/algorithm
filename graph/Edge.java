package graph;

/**
 * 带权重的边的数据类型
 */
public class Edge implements Comparable<Edge> {
    private final int v; // 顶点1
    private final int w; // 顶点2
    private final double weight; // 权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v)
            return w;
        else if (vertex == w)
            return v;
        else
            throw new RuntimeException("Inconsistent edge");
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight() < o.weight())
            return -1;
        else if (this.weight() > o.weight())
            return +1;
        else
            return 0;
    }

    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}