package com.example.dsandalgo.aaaa.leecode;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public static int maxProfits(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        return process(prices, 0, false, 0);


    }

    /**
     * @param prices   原始数组
     * @param index    当前位置
     * @param buy      是否已经购买过股票
     * @param buyPrice 购买价格
     * @return 最好的收益
     */
    private static int process(int[] prices, int index, boolean buy, int buyPrice) {
        if (index > prices.length) {
            return 0;
        }
        if (buy) {
            //卖
            int yesSell = prices[index] - buyPrice + process(prices, index + 2, false, 0);
            // 不卖
            int noSell = process(prices, index + 1, true, buyPrice);
            return Math.max(yesSell, noSell);
        } else {
            //此时买
            int yesBuy = process(prices, index + 1, false, prices[index]);
            //此时不买
            int noBuy = process(prices, index + 1, true, 0);
            return Math.max(yesBuy, noBuy);

        }
    }

}
