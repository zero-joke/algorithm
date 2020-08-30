package algs4.Strings;

/**
 * 低位优先的字符串排序
 */
public class LSD {

    private static final int R = 256;

    // 通过前W个字符将a[]排序
    public static void sort(String[] a, int W) {
        int N = a.length;
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; --d) {
            int[] count = new int[R];
            for (String s : a) {
                count[s.charAt(d)]++; // 计算出现频率
            }
            for (int i = 0; i < R; ++i) {
                count[i + 1] += count[i]; // 将频率转换为索引
            }
            for (int i = 0; i < N; i++) // 将元素分类
                aux[count[a[i].charAt(d)]++] = a[i];
            for (int i = 0; i < N; i++) // 回写
                a[i] = aux[i];
        }
    }
}