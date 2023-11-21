package com.example.dsandalgo.aaaa.leecode;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class BestTimeToBuyAndSellStockIV {
    public static int bestProfit(int[] prices, int k) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][k + 1];

        for (int j = 1; j < k + 1; j++) {
            dp[1][j] = dp[0][j];
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(dp[1][j], best + prices[1]);
            for (int i = 2; i < n; i++) {
                dp[i][j] = dp[i - 1][j];
                int cur = dp[i][j - 1] - prices[i];
                best = Math.max(cur, best);
                dp[i][j] = Math.max(dp[i][j], best + prices[i]);

            }
        }

        return dp[n - 1][k];

    }

    public static void main(String[] args) {
        System.out.println(Math.pow(10, 2));
        String v = "";
    }
}
