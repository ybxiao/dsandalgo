package com.example.dsandalgo.aaaa.dp;

/**
 * https://leetcode.com/problems/interleaving-string/
 */
public class InterleavingString {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }

        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        char[] arr3 = s3.toCharArray();
        if (arr3.length != (arr1.length + arr2.length)) {
            return false;
        }
        //dp[i][j]代表的含义：从 arr1拿去前 i 个字符，从 arr2获取前 j 个字符，
        // 是否能够组成 arr3前 i + j个字符
        boolean[][] dp = new boolean[arr1.length + 1][arr2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= arr1.length; i++) {
            if (arr1[i - 1] != arr3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int i = 1; i <= arr2.length; i++) {
            if (arr2[i - 1] != arr3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }

        for (int i = 1; i < arr1.length; i++) {
            for (int j = 1; j < arr2.length; j++) {

                if (

                        arr1[i] == arr3[i + j - 1] && dp[i - 1][j] ||
                                arr2[j] == arr3[i + j - 1] && dp[i][j - 1]


                ) {
                    dp[i][j] = true;
                }

            }
        }
        return dp[arr1.length][arr2.length];

    }
}
