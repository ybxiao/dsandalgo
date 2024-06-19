package com.example.dsandalgo.aaaa.dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 题目：
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如 s1 = "abcde"，s2 = "axbc"
 * 返回 1
 */
public class DeleteMinCost {
    /**
     * 使用暴力方法，找出s2所有的子串，按照长度从大到小排序
     * 依次找出s2所有的子串和s1进行匹配，如果某个字串是s1的子串，那么此时的子串就是s2最少删除后的样子
     *
     * @param s1
     * @param s2 这个方法适用于s2长度比较短的case
     * @return
     */
    public static int minCost(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        List<String> s2SubStrings = new ArrayList<>();
        char[] charArray = s2.toCharArray();
        process(0, charArray, "", s2SubStrings);
        s2SubStrings.sort((o1, o2) -> o2.length() - o1.length());
        for (String participate :
                s2SubStrings) {
            // O(N)
            if (participate.contains(s1)) {
                return s2.length() - participate.length();
            }
        }
        return s2.length();
    }

    /**
     * 返回s2的所有子串
     *
     * @param index
     * @param s2
     * @param path
     * @param subStrs
     */
    public static void process(int index, char[] s2, String path, List<String> subStrs) {
        if (index == s2.length) {
            subStrs.add(path);
            return;
        }
        process(index + 1, s2, path, subStrs);
        process(index + 1, s2, path + s2[index], subStrs);
    }

    /**
     * 找出s1所有的子串，然后求s2到每一个子串的最小删除距离
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int minCost2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] s1CharArray = s1.toCharArray();
        int ans = s2.length();
        for (int start = 0; start < s1CharArray.length; start++) {
            for (int end = start + 1; end <= s1CharArray.length; end++) {
                ans = Math.min(ans, distance(s2.toCharArray(), s1.substring(start, end).toCharArray()));
            }
        }
        return ans;

    }

    /**
     * @param s2
     * @param s1
     * @return s2最少删除多少个字符变成s1
     */
    private static int distance(char[] s2, char[] s1) {
        if (s2.length < s1.length) {
            return Integer.MAX_VALUE;
        }
        int m = s2.length;
        int n = s1.length;
        //dp[i][j]: s2 前0...i个字符 变成 s1前0...j 个字符
        int[][] dp = new int[m][n];
        dp[0][0] = s2[0] == s1[0] ? 0 : Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            dp[i][0] = (s2[i] == s1[0] || dp[i - 1][0] != Integer.MAX_VALUE) ? i - 1 : Integer.MAX_VALUE;
        }
        for (int row = 1; row < m; row++) {
            for (int col = 0; col < n && col <= row; col++) {
                dp[row][col] = dp[row - 1][col];
                if (s2[row] == s1[col] && dp[row - 1][col - 1] != Integer.MAX_VALUE) {
                    dp[row][col] = Math.min(dp[row][col], dp[row - 1][col - 1] + 1);
                }

            }
        }

        return dp[m - 1][n - 1];

    }

    public static int minCost3(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();
        int m = s2CharArray.length;
        int n = s1CharArray.length;
        //dp[i][j] s2中 0...i 至少删除多少个字符变成s1中0...j
        int[][] dp = new int[m][n];
        //最差的情况，s2只有删除成空串才能变成s1的子串
        int ans = m;
        for (int start = 0; start < n; start++) {
            //dp[0][0];
            //先把start列填完
            //m 表示的是无论如何也没发达成目标
            dp[0][start] = s2CharArray[0] == s1CharArray[start] ? 0 : m;
            for (int row = 1; row < m; row++) {
                dp[row][start] =
                        (s2CharArray[row] == s1CharArray[start] || dp[row - 1][start] != m) ? row : m;
            }
            ans = Math.min(ans, dp[m - 1][start]);
            for (int end = start + 1; end < n && end - start < m; end++) {

                //先填第一行
                //0...first -1 行不用管，因为m>n
                int first = end - start;
                dp[first][end] = (s2CharArray[first] == s1CharArray[end] && dp[first - 1][end - 1] == 0) ? 0 : m;
                for (int i = first + 1; i < m; i++) {
                    if (dp[i - 1][first] != m) {
                        dp[i][first] = dp[i - 1][first] + 1;
                    }

                    if (s2CharArray[i] == s1CharArray[first] && dp[i - 1][first - 1] != m) {
                        dp[i][first] = Math.min(dp[i][first], dp[i - 1][first - 1]);
                    }
                }
                ans = Math.min(ans, dp[m - 1][first]);

            }


        }

        return ans;

    }
}
