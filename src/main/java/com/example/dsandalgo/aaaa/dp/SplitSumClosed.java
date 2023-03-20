package com.example.dsandalgo.aaaa.dp;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 * <p>
 * 子集/组合问题 也可以理解为背包问题变形
 * 暴力尝试：从左往右尝试，每个位置上的数字有"选"和"不选"两种可能性
 */
public class SplitSumClosed {

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return process(arr, 0, sum / 2);

    }

    /**
     * @param arr   原始数组
     * @param index 当前做选择的位置
     * @param rest  自由选择数字的累加和不能超过rest
     * @return 从arr[index]往后的位置自由选择，返回不超过rest的最大累加和
     */
    public static int process(int[] arr, int index, int rest) {
        //没有可选择的数了，此时累加和为0
        //通过上层调用方来控制rest不会出现小于0的情况
        if (index == arr.length) {
            return 0;
        } else {
            //当前还有数字可以选择，可以选择"要"或者"不要"这个数字，枚举所有的可能性
            // 要
            int p1 = 0;
            if (rest - arr[index] >= 0) {
                p1 = arr[index] + process(arr, index + 1, rest - arr[index]);
            }
            // 不要
            int p2 = process(arr, index + 1, rest);
            return Math.max(p1, p2);
        }
    }


    //两个可变参数的动态规划表
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        int rest = sum / 2;
        //dp[i][j] 代表在i...n位置上数字自由选择，不超过j的最大累加和
        int[][] dp = new int[n + 1][rest + 1];
        for (int i = 0; i <= rest; i++) {
            dp[n][i] = 1;
        }
        for (int index = n-1; index >= 0; index--) {
            for (int j = 0; j < rest; j++) {
                dp[index][j] = dp[index +1][j];
                if (j - arr[index] >= 0){
                    dp[index][j] = Math.max(dp[index][j], arr[index] + dp[index+1][j-arr[index]]);
                }

            }

        }
        return dp[0][rest];
    }

}
