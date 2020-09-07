package Exercises.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 冗余连接 II
 * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
 * 
 * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N)
 * 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 
 * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
 * 
 * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 * 
 * 示例 1:
 * 
 * 输入: [[1,2], [1,3], [2,3]] 输出: [2,3] 解释: 给定的有向图如下: 1 / \ v v 2-->3 示例 2:
 * 
 * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]] 输出: [4,1] 解释: 给定的有向图如下: 5 <- 1 -> 2 ^
 * | | v 4 <- 3 注意:
 * 
 * 二维数组大小的在3到1000范围内。 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
 */
public class DirectedConnection {
    private int N;
    private Map<Integer, LinkedList<Integer>> pres = new HashMap<>();

    public int[] findRedundantDirectedConnection(int[][] edges) {
        N = edges.length;
        Integer cycleItem = null;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (pres.containsKey(to)) {
                cycleItem = to;
            } else {
                pres.put(to, new LinkedList<>());
            }
            pres.get(to).addLast(from);
        }
        if (cycleItem != null) {
            int p1 = pres.get(cycleItem).getFirst();
            int p2 = pres.get(cycleItem).getLast();
            Stack res1 = getCycle(p1, p1, new Stack());
            Stack res2 = getCycle(p2, p2, new Stack());
            if (res1 == null && res2 == null) {
                // 没有环
                int[] tup = new int[2];
                tup[0] = pres.get(cycleItem).getLast();
                tup[1] = cycleItem;
                return tup;
            } else {
                // 有环
                int[] tup = new int[2];
                tup[0] = res1 != null ? p1 : p2;
                tup[1] = cycleItem;
                return tup;
            }
        } else {
            for (int i = 1; i <= N; ++i) {
                Stack res = getCycle(i, i, new Stack());
                if (res != null) {
                    return getDupEdge(res, edges);
                }
            }
        }
        return null;
    }

    private int[] getDupEdge(Stack stack, int[][] edges) {
        int[] res = new int[2];
        for (int[] edge : edges) {
            stack.forEach(item -> {
                int[] temp = (int[]) item;
                if (temp[0] == edge[0] && temp[1] == edge[1]) {
                    res[0] = temp[0];
                    res[1] = temp[1];
                }
            });
        }
        return res;
    }

    private Stack getCycle(int cycleItem, int cur, Stack stack) {
        if (pres.containsKey(cur)) {
            for (Integer pre : pres.get(cur)) {
                int[] tup = new int[2];
                tup[0] = pre;
                tup[1] = cur;
                stack.push(tup);
                if (pre == cycleItem) {
                    return stack;
                } else {
                    Stack res = getCycle(cycleItem, pre, stack);
                    if (res != null) {
                        return res;
                    }
                }
                stack.pop();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DirectedConnection connection = new DirectedConnection();
        int[][] edges = new int[][] { { 2, 1 }, { 3, 1 }, { 4, 2 }, { 1, 4 } };
        int[] res = connection.findRedundantDirectedConnection(edges);
        System.out.println(res.length);
    }
}
