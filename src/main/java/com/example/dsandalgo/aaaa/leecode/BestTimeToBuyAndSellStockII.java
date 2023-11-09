package com.example.dsandalgo.aaaa.leecode;

/**
 * 可以做无限次交易
 */
public class BestTimeToBuyAndSellStockII {

    public static int bestProfit(int[] price) {
        if (price == null || price.length < 2) {
            return 0;
        }
        int ans = 0;
        int pre = price[0];
        for (int i = 1; i < price.length; i++) {
            ans += Math.max(price[i] - pre, 0);
            pre = price[i];
        }
        return ans;
    }
}
