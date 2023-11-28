package com.example.dsandalgo.aaaa.leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/freedom-trail/
 */
public class FreedomTrail {
    public static int findRotateSteps(String ring, String key) {
        if (ring == null || key == null || ring.length() == 0) {
            return 0;
        }
        char[] rings = ring.toCharArray();
        char[] keys = key.toCharArray();
        int n = rings.length;
        int m = keys.length;
        //rings中的字符在哪些位置有
        Map<Character, List<Integer>> posMap = new HashMap<>();
        for (int i = 0; i < rings.length; i++) {
            if (!posMap.containsKey(rings[i])) {
                posMap.put(rings[i], new ArrayList<>());
            }
            posMap.get(rings[i]).add(i);
        }
        //dp[i][j] : 当前圆盘的指针在i位置，完成 j...m的最小步数
        int[][] dp = new int[n][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        return process(0, 0, keys, posMap, dp, n);
    }

    private static int process(int preButton, int index, char[] keys, Map<Character, List<Integer>> posMap, int[][] dp, int n) {
        if (dp[preButton][index] != -1) {
            return dp[preButton][index];
        } else {
            int ans = Integer.MAX_VALUE;
            if (index == n) {
                ans = 0;
            } else {
                List<Integer> nextPositions = posMap.get(keys[index]);
                for (Integer next :
                        nextPositions) {
                    ans = Math.min(ans, dail(index, next, n) + 1 + process(next, index + 1, keys, posMap, dp, n));
                }

            }
            dp[preButton][index] = ans;
            return dp[preButton][index];
        }


    }

    private static int dail(int index, int next, int n) {
        return Math.min(Math.abs(index - next), Math.min(index, next) + n - Math.max(index, next));
    }


}
