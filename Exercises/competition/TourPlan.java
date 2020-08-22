import java.util.*;

public class TourPlan {
    public int maxWeight(int[][] edges, int[] value) {
        Map<Integer, List<Integer>> adjmap = new HashMap<>();
        for(int i=0;i<edges.length;++i) {
            if(!adjmap.containsKey(edges[i][0]))
                adjmap.put(edges[i][0], new ArrayList<Integer>());
            if(!adjmap.containsKey(edges[i][1]))
                adjmap.put(edges[i][1], new ArrayList<Integer>());
            adjmap.get(edges[i][0]).add(edges[i][1]);
            adjmap.get(edges[i][1]).add(edges[i][0]);
        }

        int max = 0;
        List<Path> paths = new ArrayList<>();
        for(Integer a: adjmap.keySet()) {
            for(Integer b: adjmap.get(a)) {
                if(adjmap.containsKey(b)) {
                    for(Integer c: adjmap.get(b)) {
                        if(c != a && adjmap.containsKey(c)
                                  && adjmap.get(c).stream().anyMatch(item->item==a)) {
                            paths.add(new Path(b, c, value[b]+value[c]));
                        }
                    }
                }
            }
            max = Math.max(max, getMax(a, paths, value));
            paths.clear();
        }
        return max;
    }

    private int getMax(int a, List<Path> paths, int[] value) {
        // Iterator<Path> iter = paths.iterator();
        // while(iter.hasNext()) {
        //     Path p = iter.next();
        //     System.out.printf("path={%d %d %d},val={%d %d %d}\n"
        //         ,a,p.b,p.c,value[a],value[p.b],value[p.c]);
        // }
        if(paths.isEmpty())
            return 0;
        int res = 0;
        for(Path top: paths) {
            int temp = value[a] + top.val;
            for(Path again: paths) {
                res = Math.max(res, temp + again.getValWithTop(top, value));
            }
        }
        return res;
    }

    private class Path implements Comparable<Path> {
        int b;
        int c;
        int val;

        public Path(int b, int c, int val){
            this.b =b; this.c=c; this.val=val;
        }

        int getValWithTop(Path top, int[] value) {
            if(b == top.b || b == top.c)
                val -=value[b];
            if(c == top.b || c == top.c)
                val -=value[c];
            return val;
        }
        @Override
	    public int compareTo(Path p) {
		    return this.val-p.val;
	    }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},{3,4},{3,5},{3,6},{3,7},{3,8},{3,9},{4,5},{4,6},{4,7},{4,8},{4,9},{5,6},{5,7},{5,8},{5,9},{6,7},{6,8},{6,9},{7,8},{7,9},{8,9}};
        int[] value = new int[]{6808,5250,74,3659,8931,1273,7545,879,7924,7710};
        TourPlan plan = new TourPlan();
        System.out.println(plan.maxWeight(edges, value));
    }
}