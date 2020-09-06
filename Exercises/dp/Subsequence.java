package Exercises.dp;

/**
 * 动态规划：子序列相关问题
 */
public class Subsequence {
    // 求最长递增子序列的长度
    public int longestIncreasingSubseq(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n]; // dp数组含义：dp[x]代表以下标x结尾的数组的最长子序列的长度
        dp[0] = 1;
        for (int i = 1; i < n; ++i) {
            int max_i = 0;
            for (int j = 0; j < i; ++j) {
                int sub_len = dp[j];
                if (arr[i] > arr[j])
                    ++sub_len;
                max_i = Math.max(max_i, sub_len);
            }
            dp[i] = max_i;
        }
        int max = dp[0];
        for (int k = 1; k < n; ++k) {
            max = Math.max(max, dp[k]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Subsequence().longestIncreasingSubseq(new int[] { 1, 4, 2, 3, 7, 5, 6, 9, 2, 10 }));
    }
}
