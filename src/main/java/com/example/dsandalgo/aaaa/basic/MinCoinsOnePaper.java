package com.example.dsandalgo.aaaa.basic;

/**
 * 动态规划中利用窗口内最大值或最小值更新结构做优化（难）
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 * <p>
 * [3,5,5,8,7,9,10,7,6,9]
 * aim = 26
 * 暴力尝试：从左往右的尝试模型
 */
public class MinCoinsOnePaper {
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    //在index...arr.length上做选择，返回组成rest钱币的最少货币数
    //暴力尝试，两个可变参数
    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int p1 = process(arr, index + 1, rest);
            int p2 = process(arr, index + 1, rest - arr[index]);

            if (p2 != Integer.MAX_VALUE) {
                p2 += 1;
            }
            return Math.min(p1, p2);

        }
    }

    //由暴力尝试 改动态规划
    //记忆化搜索的方法直接跳过
    //时间复杂图 O(arr.length *  aim)
    public static int dp1(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j]= dp[i+1][j];
                if (j - arr[i] >= 0){
                    dp[i][j]= Math.min(dp[i][j], dp[i+1][j-arr[i]]);
                }
            }

        }
        return dp[0][aim];
    }

}
