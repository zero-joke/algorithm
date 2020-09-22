package Exercises.string;

import java.util.LinkedList;

public class Regular {
    private String par;
    private LinkedList<Star> stars = new LinkedList<>();

    Regular(String par) {
        this.par = par;
    }

    private class Star {
        int idx; // * 在模式串的下标
        int from; // 匹配目标串的开始下标
        int len; // 匹配长度

        Star(int idx, int from, int len) {
            this.idx = idx;
            this.from = from;
            this.len = len;
        }
    }

    // * 和 .
    // (1) 如果模式串以*开头，报错
    // (2) 否则，如果模式串当前指向的字符的下一个字符为*, 匹配目标串尽量多的字符
    // (3) 否则, 如果当前位置match，匹配下一位
    // (4) 否则, 不匹配（下一位是*的情况（2）已包含）, 从已匹配的*链中取出最后一个,如果该star匹配有目前串字符
    // ，则试图减少一位*的匹配长度，前提是*后面的字符跟*匹配的字符一致。
    private boolean match(String str) {
        int N = str.length();
        int M = par.length();
        int i = 0, j = 0;
        while (true) {
            if (i == N && j == M)
                return true;
            if(i == N && canMatchEmpty(j))
                return true;
            if (i < N && j < M) {
                char c = str.charAt(i);
                char p = par.charAt(j);
                if (p == '*') {
                    return false;
                }
                if (j + 1 < M && par.charAt(j + 1) == '*') {
                    // 尽可能匹配多的目标串中的字符
                    int from = i;
                    int len = 0;
                    while (i < N && match(str.charAt(i), p)) {
                        len++;
                        i++;
                    }
                    if (i == N && canMatchEmpty(j + 2)) {
                        return true;
                    }
                    stars.addLast(new Star(j + 1, from, len));
                    j += 2;
                    continue;
                }
                if (match(c, p)) {
                    i++;
                    j++;
                    continue;
                }
            }
            // 不匹配
            while (!stars.isEmpty()) {
                Star s = stars.getLast();
                if (s.len > 0 && s.idx + 1 < M && match(str.charAt(s.from), par.charAt(s.idx + 1))) {
                    s.len--;
                    j = s.idx + 1;
                    i = s.from + s.len;
                    break;
                } else {
                    stars.removeLast();
                }
            }
            if (stars.isEmpty()) {
                return false;
            }
        }
    }

    private boolean match(char c, char p) {
        return c == p || p == '.';
    }

    private boolean canMatchEmpty(int idx) {
        while (idx < par.length()) {
            if (par.charAt(idx) == '*' || idx + 1 >= par.length() || par.charAt(idx + 1) != '*') {
                return false;
            }
            idx += 2;
        }
        return true;
    }
    
    public static void main(String[] args) {
        // Regular regular = new Regular("a*be*ef.i");
        Regular regular = new Regular("be*f*wxii*.*");
        System.out.println(regular.match("beeefwi"));
    }
}
