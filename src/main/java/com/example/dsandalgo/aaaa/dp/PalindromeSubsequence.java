package com.example.dsandalgo.aaaa.dp;


/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度
 * 尝试模型：
 * 从左往右
 * 范围
 * 全样本对应的
 * 业务限制的
 * <p>
 * 从尝试上比较像范围上的尝试模型
 */
public class PalindromeSubsequence {
    public static int longestPalindromeSeq1(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;

        return process(str, 0, n - 1);

    }

    //返回str[l...r]范围上的最长回文子序列
    private static int process(char[] str, int l, int r) {
        //base case
        //通过base case来递推出最后的答案
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return str[l] == str[r] ? 2 : 1;
        }
        int p1 = process(str, l + 1, r);
        int p2 = process(str, l, r - 1);
        int p3 = process(str, l + 1, r - 1);
        int p4 = str[l] == str[r] ? (2 + p3) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int lps2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }

        for (int i = n-3 ; i >= 0 ; i--) {
            for (int j = 0; j < n-1; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], (2 + dp[i + 1][j - 1]));
                }
            }

        }

        return dp[0][n - 1];
    }

}
