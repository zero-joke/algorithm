package Exercises.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 除法求值 给出方程式 A / B = k, 其中 A 和 B 均为用字符串表示的变量， k
 * 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，则返回 -1.0。
 * 
 * 示例 : 给定 a / b = 2.0, b / c = 3.0 问题: a / c = ?, b / a = ?, a / e = ?, a / a =
 * ?, x / x = ?  返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 
 * 输入为: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 equations.size() ==
 * values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果值均为正数。以上为方程式的描述。 返回vector<double>类型。
 * 
 * 基于上述例子，输入如下：
 * 
 * equations(方程式) = [ ["a", "b"], ["b", "c"] ], values(方程式结果) = [2.0, 3.0],
 * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"]
 * ]. 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
 */
public class Equation {
    private Map<String, List<Edge>> adj = new HashMap<>();

    // 生成图并使用深度搜索
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); ++i) {
            List<String> item = equations.get(i);
            String from = item.get(0);
            String to = item.get(1);
            if (!adj.containsKey(from)) {
                adj.put(from, new ArrayList<>());
            }
            if (!adj.containsKey(to)) {
                adj.put(to, new ArrayList<Edge>());
            }
            adj.get(from).add(new Edge(from, to, values[i]));
            adj.get(to).add(new Edge(to, from, 1 / values[i]));
        }

        double[] answers = new double[queries.size()];
        for (int j = 0; j < queries.size(); ++j) {
            List<String> que = queries.get(j);
            String from = que.get(0);
            String end = que.get(1);
            answers[j] = dfs(from, end, new HashSet<String>());
        }
        return answers;
    }

    private double dfs(String from, String end, Set<String> marked) {
        marked.add(from);
        if (adj.containsKey(from)) {
            List<Edge> edges = adj.get(from);
            Edge endE = edges.stream().filter(item -> item.to.equals(end)).findFirst().orElse(null);
            if (endE != null) {
                return endE.val;
            } else {
                for (Edge edge : edges) {
                    if (!marked.contains(edge.to)) {
                        double res = dfs(edge.to, end, marked);
                        if (res != -1.0) {
                            return res * edge.val;
                        }
                    }
                }
                // all fail
                return -1.0;
            }
        } else {
            return -1.0;
        }
    }

    private class Edge {
        String from;
        String to;
        double val;

        Edge(String from, String to, double val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }
}