package com.example.dsandalgo.aaaa.dp;

import com.google.common.collect.Lists;

/**
 * 经典的背包问题
 * 一组货物有：
 * 不同的重量
 * 不同的价值
 * 求不超过bag容量的情况下，选择货物的最大价值
 */
public class KnapsackCopy2023 {

    // weights 代表一组货物的重量
    // values 代表一组货物的价值
    // bag 代表背包容量
    // 返回不超过bag容量的前提下的最大价值
    // 从左往右的尝试模型，求 ：dp[0][rest]
    public static int maxValue(int[] weights, int[] values, int bag) {
        //参数过滤
        if (weights == null || weights.length == 0 || values == null || values.length == 0) {
            return 0;
        }
        int n = weights.length;
        int[][] dp = new int[n + 1][bag + 1];
        for (int i = n-1; i >=0 ; i--) {
            for (int j = 0; j <= bag; j++) {
                // 当在i位置不选择货物时
                dp[i][j] = dp[i+1][j];
                // 当在i位置可选择货物时
                if (j- weights[i] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i+1][j-weights[i]] + values[i]);
                }
            }

        }
        return dp[0][bag];

    }


}
