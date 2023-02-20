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
public class CoinsWayNoLimit {
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(0, aim, arr);
    }

    //arr[index ...]上做选择，恰好组成rest这么多钱币的方法数
    // 0 .. index上的钱币都已经做了选择，所带来的影响是放入rest里面了
    private static int process(int index, int rest, int[] arr) {
        // 当没有钱币可选时，如果剩余钱币数恰好为0 返回一种方法，否则返回0种方法
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        //枚举使用1、2、3、4...张 arr[index]钱币的情况
        //需要满足条件，rest不能小于 0
        for (int i = 0; rest - i * arr[index] >= 0; i++) {
            ways += process(index + 1, rest - i * arr[index], arr);
        }
        return ways;

    }


    private static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n][aim + 1];
        dp[n][0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int rest= 0 ; rest <= aim ; rest++) {
                for (int j = 0; j < rest - j * arr[i]; j++) {
                    dp[i][rest] += dp[i +1][rest -j * arr[i]];
                }

            }

        }
        return dp[0][aim];
    }
    public static int dp2(int[] arr, int aim){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int  n = arr.length;
        int[][] dp = new int[n][aim+1];
        dp[n][0]  =1;
        for (int i = n-1; i >=0 ; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i+1 ][rest];
                if (rest - arr[i] >= 0){
                    dp[i][rest] += dp[i][rest-arr[i]];
                }
            }

        }

        return dp[0][aim];

    }
}
