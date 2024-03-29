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
 * 范围上的尝试模型，和扑克牌一样
 */
public class PalindromeSubsequenceCopy2023 {
    public static int longestPalindromeSeq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] charArray = s.toCharArray();
        int n = s.length();
        return process(charArray, 0, n);

    }

    //返回 arr l ...r 范围上的最长公共子序列
    private static int process(char[] arr, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return arr[l] == arr[r] ? 2 : 0;
        }
        int p1 = process(arr, l + 1, r);
        int p2 = process(arr, l, r - 1);
        int p3 = process(arr, l + 1, r - 1);
        int p4 = arr[l] == arr[r] ? (2 + p3) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    //两个可变参数，因此动态规划就是二维的
    public static int lps2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = charArray[i] == charArray[i + 1] ? 2 : 0;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n; j > 2; j--) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (charArray[i] == charArray[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }

        }

        return dp[0][n - 1];
    }

}
