package Fundamental.base;

import java.util.Arrays;

/**
 * 并查集：加权&路径压缩
 */
public class UF {

    private int count;
    private int id[]; // 分量id
    private int sz[]; // 分量的大小

    public UF(int n) {
        count = n;
        id = new int[n];
        sz = new int[n];
        Arrays.fill(sz, 1);
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    // 在p和q之间添加一条连接
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j)
            return;
        // 将小树的根节点连接到大树的根节点
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        count--;
    }

    // p(0 到 N-1)所在的分量的标识符
    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]]; // 路径压缩
            p = id[p];
        }
        return p;
    }

    // 如果 p 和 q 存在于同一个分量中则返回 true
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 连通分量的数量
    public int count() {
        return count;
    }
}