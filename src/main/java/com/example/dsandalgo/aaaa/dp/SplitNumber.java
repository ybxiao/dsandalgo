package com.example.dsandalgo.aaaa.dp;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class SplitNumber {

    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);

    }

    /**
     * @param pre  上一个拆出来的数是pre
     * @param rest 当下还剩下未分的数
     * @return 返回拆解的方法数
     */
    private static int process(int pre, int rest) {
        //base case怎么写决定了函数的返回值
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(pre, rest - pre);
        }

        return ways;
    }

    private static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //dp[i][j]:上一次拆出来的数是i ，剩余的数是j ，拆分的方法数
        int[][] dp = new int[n + 1][n + 1];

        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }

        for (int pre = n-1 ; pre >0 ; pre--) {
            for (int rest = pre; rest <=n ; rest++) {
                int ways = 0;
                for (int i = pre; i <= rest  ; i++) {
                    ways += dp[i][rest-i];
                }
                dp[pre ][rest] = ways;
            }
        }

        return dp[1][n];
    }

}
