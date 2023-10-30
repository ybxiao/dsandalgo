package com.example.dsandalgo.aaaa.basic;

import java.util.Arrays;

public class PostOfficeProblem {
    public static int minDistance(int[] A, int k) {
        if (A == null || A.length == 0 || k <= 0) {
            return 0;
        }
        Arrays.sort(A);
        int n = A.length;
        int[][] dp = new int[n + 1][k + 1];
        int[][] cost = initCost(A, n);
        for (int i = 0; i <= n; i++) {
            dp[i][1] = cost[0][i - 1];
        }
        for (int nk = 2; nk <= k; nk++) {
            for (int i = n; i >= nk; i--) {
                dp[i][nk] = Integer.MAX_VALUE;
                for (int j = nk - 1; j < i; j++) {
                    dp[i][nk] = Math.min(dp[i][nk], dp[j][1] + cost[j][i - 1]);
                }
            }
        }
        return dp[n][k];
    }

    private static int[][] initCost(int[] A, int n) {
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                cost[i][j] = cost[i][j - 1] + A[j] - A[(i + j) / 2];
            }
        }
        return cost;
    }
}
