package com.example.dsandalgo.aaaa.dp;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 * <p>
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * <p>
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class CoinsWayNoLimitCopy2023 {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, aim, 0);

    }

    /**
     * @param arr
     * @param rest
     * @param index
     * @return 在arr[index...]任意选择货币，每张货币数不限，返回恰好凑成
     * rest这么多钱的方法数
     */
    private static int process(int[] arr, int rest, int index) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        //枚举当前货币用的张数
        for (int zhang = 0; rest - zhang * arr[index] >= 0; zhang++) {
            res += process(arr, rest - zhang * arr[index], index + 1);
        }

        return res;


    }

    private static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = 0;
                for (int zhang = 0; rest - zhang * arr[i] >= 0; zhang++) {
                    dp[i][rest] = dp[i + 1][rest - zhang * arr[i]]

                }
            }

        }

        return dp[0][0];
    }

    private static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i++) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i][rest - arr[i]];
                }
            }

        }
        return dp[0][0];
    }
}
