package Exercises.dp;

import java.util.Arrays;

/**
 * 凑零钱问题
 */
public class CoinChange {
    // 问题1：凑出给定金额需要的最少硬币数（可以假设每种面值的硬币数量无限）
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // dp数组含义：dp[x]表示凑出x块钱需要的最少硬币数
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            for (int coin : coins) {
                if (i < coin)
                    continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    // 问题2：凑出给定金额可能的所有组合数量（可以假设每种面值的硬币数量无限）
    public int changeTotal(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // dp数组含义：dp[x]表示凑出x块钱可能的所有组合数量
        // base case: 凑出最小面值金额的组合量为1
        dp[0] = 1;
        for (int i = 1; i <= amount; ++i) {
            for (int coin : coins) {
                if (i < coin)
                    continue;
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange().changeTotal(5, new int[] { 1, 2, 5 }));
    }
}
