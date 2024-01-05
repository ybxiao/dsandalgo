package com.example.dsandalgo.aaaa.dp;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * <p> 全样本对应模型
 * https://leetcode.com/problems/longest-common-subsequence/
 */
public class LongestCommonSubsequenceCopy2023V2 {

    public static int longestCommonSubsequence1(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return 0;
        }
        char[] str1CharArray = str1.toCharArray();
        char[] str2CharArray = str2.toCharArray();
        return process1(str1CharArray, str2CharArray, str1CharArray.length - 1, str2CharArray.length - 1);
    }

    //求strChars1[0...i]和strChars2[0...j]的最长公共子序列
    //可能性划分
    //a) 最长公共子序列一定以strChars1[i]结尾，也一定以strChars2[j]结尾
    //b) 最长公共子序列可能以strChars1[i]结尾，一定不以strChars2[j]结尾
    //c) 最长公共子序列一定不以strChars1[i]结尾，可能以strChars2[j]结尾
    //d) 最长公共子序列一定不以strChars1[i]结尾，也一定不以strChars2[j]结尾
    //在这四种可能性的划分之上，还要考虑特殊边界场景。i==0 j==0 i == j == 0的场景
    private static int process1(char[] strChars1, char[] strChars2, int i, int j) {
        if (i == 0 && j == 0) {
            return strChars1[i] == strChars2[j] ? 1 : 0;
        }
        if (i == 0) {
            if (strChars1[i] == strChars2[j]) {
                return 1;
            } else {
                return process1(strChars1, strChars2, i, j - 1);
            }
        }
        if (j == 0) {
            if (strChars1[i] == strChars2[j]) {
                return 1;
            } else {
                return process1(strChars1, strChars2, i - 1, j);
            }
        }

        //这里其实是舍弃了 a)的这种可能性的结果的。因为b) 和 c)的字符串的范围是包含a)的，因此a)的结果是一定小于b)和c)的。
        int p1 = process1(strChars1, strChars2, i - 1, j);
        int p2 = process1(strChars1, strChars2, i, j - 1);
        int p3 = 0;
        if (strChars1[i] == strChars2[j]) {
            p3 = Math.max(p3, process1(strChars1, strChars2, i - 1, j - 1) + 2);
        }

        return Math.max(Math.max(p1, p2), p3);


    }

    // dp 动态规划的方式
    public static int longestCommonSubsequence2(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return 0;
        }
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int[][] dp = new int[charArray1.length][charArray2.length];
        dp[0][0] = charArray1[0] == charArray2[0] ? 1 : 0;
        for (int i = 1; i < charArray1.length; i++) {
            if (charArray1[0] == charArray2[i]) {
                dp[0][i] = 1;
            }
            dp[0][i] = Math.max(dp[0][i], dp[0][i - 1]);
        }

        for (int i = 1; i < charArray1.length; i++) {
            if (charArray2[0] == charArray1[i]) {
                dp[i][0] = 1;
            }
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][0]);
        }
        for (int i = 1; i < charArray1.length; i++) {
            for (int j = 1; j < charArray2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (charArray1[i] == charArray2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 2);
                }
            }
        }

        return dp[charArray1.length - 1][str2.toCharArray().length - 1];

    }


}
