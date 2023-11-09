package com.example.dsandalgo.aaaa.leecode;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class BestTimeToBuyAndSellStockIII {
    public static int bestProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int doneOnceBuyMax = -prices[0];
        int doneOnceMax = 0;
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, doneOnceBuyMax + prices[i]);
            doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
            doneOnceBuyMax = Math.max(doneOnceBuyMax, doneOnceMax - prices[i]);
        }

        return ans;

    }
}
