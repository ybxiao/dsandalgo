package com.example.dsandalgo.aaaa.leecode;

/**
 * 只做一次交易，求最大收益
 */
public class BestTimeToBuyAndSellStock {

    public static int bestProfit(int[] price) {
        if (price == null || price.length < 2) {
            return 0;
        }
        int max = 0;
        int leftMin = price[0];
        for (int i = 1; i < price.length; i++) {
            leftMin = Math.min(leftMin, price[i]);
            max = Math.max(max, price[i] - leftMin);
        }
        return max;
    }
}
