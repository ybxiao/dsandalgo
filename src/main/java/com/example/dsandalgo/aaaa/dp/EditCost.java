package com.example.dsandalgo.aaaa.dp;

public class EditCost {
    public static int minCost(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int N = c1.length;
        int M = c2.length;
        int[][] dp = new int[N + 1][M + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            dp[i][0] = i * dc;
        }
        for (int j = 1; j <= M; j++) {
            dp[0][j] = j * ic;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (c1[i] == c2[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + rc);
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                }

            }


        }

        return dp[N][M];
    }

    public static void main(String[] args) {
        String str1 = "abcdf";
        String str2 = "";
        System.out.println(minCost(str1, str2, 2, 9, 8));
    }

}
