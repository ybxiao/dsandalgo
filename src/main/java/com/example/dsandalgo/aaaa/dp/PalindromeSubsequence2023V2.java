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
public class PalindromeSubsequence2023V2 {

    public static int lps(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        char[] charArray = input.toCharArray();
        return process(charArray, 0, charArray.length - 1);
    }

    /**
     * @param charArray
     * @param l         左边界
     * @param r         有边界
     * @return 返回数组charArray在l ... r之间的最长回文子序列长度
     * 自顶向下
     * 自底向上
     */
    private static int process(char[] charArray, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return charArray[l] == charArray[r - 1] ? 2 : 1;
        }
        //
        int n1 = 0;
        if (charArray[l] == charArray[r]) {
            n1 = process(charArray, l + 1, r - 1) + 2;
        }

        int n2 = process(charArray, l + 1, r);
        int n3 = process(charArray, l, r - 1);
        int n4 = process(charArray, l + 1, r - 1);


        return Math.max(Math.max(n1, n4), Math.max(n2, n3));
    }


    // dp[i][j] 表示input i...j位置上的最长回文子序列
    public static int lps2(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        char[] charArray = input.toCharArray();
        int n = charArray.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int l = n - 2; l >= 0; l--) {
            for (int r = l + 1; r < n; r++) {
                dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                if (charArray[l] == charArray[r]) {
                    dp[l][r] = Math.max(dp[l][r], dp[l + 1][r - 1] + 2);
                }

            }

        }
        //printMatrix(dp);

        return dp[0][n - 1];

    }

    public static void printMatrix(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.println(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(lps2("bbbab"));
    }

}
