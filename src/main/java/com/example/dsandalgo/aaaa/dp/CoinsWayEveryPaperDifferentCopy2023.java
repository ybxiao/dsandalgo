package com.example.dsandalgo.aaaa.dp;


import java.sql.PreparedStatement;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class CoinsWayEveryPaperDifferentCopy2023 {

    public static int coinsWay(int[] arr, int aim) {
        return p(arr, 0, aim);
    }

    /**
     * @param arr
     * @param index 在arr从index...往后做选择
     * @param rest  恰好组成rest这么多钱的方法数
     * @return
     */
    private static int p(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (rest == 0) {
            return 1;
        }
        //1 不使用当前货币
        int p1 = p(arr, index + 1, rest);
        //2 使用当前货币
        int p2 = 0;
        if (rest - arr[index] >= 0) {
            p2 = p(arr, index + 1, rest - arr[index]);
        }
        return p1 + p2;
    }


    /**
     * dp[i][j]代表的含义是：arr[i...]的钱币自由选择，
     * 恰好能够凑购j这么多钱的方法数
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int dp(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];

        for (int index = 0; index <= n; index++) {
            dp[index][0] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] += dp[i + 1][j - arr[i]];
                }
            }
        }

        return dp[0][0];
    }


    //对数器
    // 1. 构造一个生成任意数组的函数
    // 数组的构建要提前准备两个变量（一个是数组的最大值，和数组的次数）
    public static int[] randomArray(int maxLen, int maxValue) {
        int length = (int) (Math.random() * maxLen) + 1;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * maxValue) + 1;
        }
        return array;
    }


    public static void printArray(int[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(arrs[i]);
        }
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 200;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] nums = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue) + 1;
            int ans1 = coinsWay(nums, aim);
            int ans2 = dp(nums, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                //printArray(nums);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
