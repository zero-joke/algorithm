package algs4.Strings;

public class KMP {
    private final int R = 256;
    private String pat;
    private int dfa[][];

    public KMP(String pat) {
        this.pat = pat;
        // init dfa
        dfa = new int[R][pat.length()];
        dfa[pat.charAt(0)][0] = 1; // 注意dfa第一步的状态是在初始时设置好的，j从1开始遍历。
        for (int X = 0, j = 1; j < pat.length(); j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String text) {
        int M = pat.length();
        int N = text.length();
        int i = 0, j = 0;
        for (; i < N && j < M; i++) {
            j = dfa[text.charAt(i)][j];
        }
        return j == M ? i - M : -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("ABABC");
        System.out.println(kmp.search("ABABDABAFCABABCABABCABABC"));
    }
}