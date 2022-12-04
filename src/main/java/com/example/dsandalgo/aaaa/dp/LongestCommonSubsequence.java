package com.example.dsandalgo.aaaa.dp;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * <p>
 * https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        char[] strChars1 = str1.toCharArray();
        char[] strChars2 = str2.toCharArray();
        //process1 函数的意义在于返回数组strChars1，strChars2，0...i 0...j位置字符的最长公共子序列
        return process1(strChars1, strChars2, strChars1.length - 1, strChars2.length - 1);
    }

    //样本对应的模型的动态规划，此类问题一般考虑以某一个位置字符开头和结尾的情况，进而推导出整体的答案
    //i j 代表的含义是分别在数组strChars1和strChars2中 0...i 范围上和0...j范围上最长的公共子序列
    //求str1和str2的最长公共子序列，分别考察两位字符串以i位置结尾的字符串和以j位置结尾的字符串的最长公共子序列
    //疑问1 ：看上去像是从右往左的动态规划，为什么不是？ 可以理解是从右往左的动态规划，但是不是从右往左的尝试，是样本对应的尝试
    //因为是从左往右或者从右往左的尝试一般是线性的，而这个尝试是一个矩阵，称为全样本对应更合适！
    //函数返回值是strChars1[0...i] strChars2[0...j]的最长公共子序列
    // a) 最长公共子序列既不以i字符结尾，又不以j字符结尾
    // b) 最长公共子序列必以i字符结尾，可能以j字符结尾
    // c) 最长公共子序列必以j字符结尾，可能以i字符结尾
    // d) 最长公共子序列必既以j字符结尾，又以i字符结尾
    // 四种情况求max
    private static int process1(char[] strChars1, char[] strChars2, int i, int j) {
        if (i == 0 && j == 0) {
            return strChars1[i] == strChars2[j] ? 1 : 0;
        } else if (i == 0) {
            if (strChars1[i] == strChars2[j]) {
                return 1;
            } else {
                return process1(strChars1, strChars2, i, j - 1);
            }
        } else if (j == 0) {
            if (strChars1[i] == strChars2[j]) {
                return 1;
            } else {
                return process1(strChars1, strChars2, i - 1, j);
            }
        } else {
            int p1 = process1(strChars1, strChars2, i, j - 1);
            int p2 = process1(strChars1, strChars2, i - 1, j);
            int p3 = 0;
            if (strChars1[i] == strChars2[j]) {
                p3 = process1(strChars1, strChars2, i - 1, j - 1) + 1;
            }
            return Math.max(p1, Math.max(p2, p3));

        }

    }

    public static int longestCommonSubsequence2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        char[] strC1 = str1.toCharArray();
        char[] strC2 = str2.toCharArray();
        int n = strC1.length;
        int m = strC2.length;
        int[][] dp = new int[n][m];
        dp[0][0] = strC1[0] == strC2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[0][i]  = strC1[0] == strC2[i] ? 1 : dp[0][i-1];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = strC1[i] == strC2[0] ? 1 : dp[i-1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] =  Math.max(dp[i-1][j], dp[i][j-1]);
                if (strC1[i] == strC2[j]){
                    dp[i][j] = Math.max(dp[i][j], (dp[i-1][j-1] +1));
                }
            }
        }

        return dp[n-1][m-1];

    }


}
