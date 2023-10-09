package com.example.dsandalgo.aaaa.dp;

import com.google.common.collect.Lists;

/**
 * 经典的背包问题
 * 一组货物有：
 * 不同的重量
 * 不同的价值
 * 求不超过bag容量的情况下，选择货物的最大价值
 */
public class Knapsack {

    // weights 代表一组货物的重量
    // values 代表一组货物的价值
    // bag 代表背包容量
    // 返回不超过bag容量的前提下的最大价值
    public static int maxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || weights.length == 0) {
            return 0;
        }

        return process(weights, values, 0, bag);

    }

    /**
     * 从左往右的尝试模型
     * index之前的货物都已经做好了选择，当前位置在index，从index位置开始进行抉择，返回不超过rest容量情况下的最大价值
     */
    public static int process(int[] weights, int[] values, int index, int rest) {
        if (index == weights.length) {
            return 0;
        }
        // 背包容量超限了
        // -1 代表怎么都做不到
        if (rest < 0) {
            return -1;
        }
        // 分两种情况，
        // 第一，选当前位置的货物
        // 只有剩余背包的容量大于当前货物的重量是才能选
        int p1 = -1;
        if (rest >= weights[index]) {
            int next = process(weights, values, index + 1, rest - weights[index]);
            if (next != -1) {
                p1 = next + values[index];
            }
        }
        //第二，不选当前位置的货物
        int p2 = process(weights, values, index + 1, rest);

        return Math.max(p1, p2);
    }


    public static int dp(int[] weights, int values[], int bag) {
        if (weights == null || values == null || weights.length != values.length || weights.length == 0) {
            return 0;
        }
        int n = weights.length;
        // 0...rest
        int[][] dp = new int[n][bag + 1];
        for (int index = n - 2; index >= 0; index--) {
            for (int rest = 1; rest <= bag; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - weights[index] >= 0) {
                    dp[index][rest] = Math.max(dp[index][rest], dp[index + 1][rest - weights[index]] + values[index]);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    public  static void  test(){
        Lists.newArrayList();
    }

}
