package com.example.dsandalgo.aaaa.basic;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 动态规划中利用窗口内最大值或最小值更新结构做优化（难）
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 * <p>
 * [3,5,5,8,7,9,10,7,6,9]
 * aim = 26
 * 暴力尝试：从左往右的尝试模型
 */
public class MinCoinsOnePaper {
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    //在index...arr.length上做选择，返回组成rest钱币的最少货币数
    //暴力尝试，两个可变参数
    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int p1 = process(arr, index + 1, rest);
            int p2 = process(arr, index + 1, rest - arr[index]);

            if (p2 != Integer.MAX_VALUE) {
                p2 += 1;
            }
            return Math.min(p1, p2);

        }
    }

    //由暴力尝试 改动态规划
    //记忆化搜索的方法直接跳过
    //时间复杂图 O(arr.length *  aim)
    public static int dp1(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - arr[i]]);
                }
            }

        }
        return dp[0][aim];
    }


    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = Maps.newHashMap();
        for (int i = 0; i < arr.length; i++) {
            if (counts.containsKey(arr[i])) {
                counts.put(arr[i], counts.get(arr[i]) + 1);
            } else {
                counts.put(arr[i], 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry :
                counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }

        return new Info(coins, zhangs);
    }

    public static int dp2(int[] arr, int aim) {
        int n = arr.length;
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                dp[index][rest] = dp[index + 1][rest];
                for (int i = 1; i <= zhangs[index] && i * zhangs[index] <= rest; i++) {
                    if (dp[index + 1][rest - i * coins[index]] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(dp[index][rest], i + dp[index + 1][rest - i * coins[index]]);
                    }
                }
            }

        }

        return dp[0][aim];
    }

    public static int dp3(int[] arr, int aim) {
        int n = arr.length;
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int mod = 0; mod <= Math.min(aim, coins[i]); mod++) {
                //mod ,mod + x, mod + 2x
                LinkedList<Integer> minWindow = new LinkedList<>();
                minWindow.add(mod);
                dp[i][mod] = dp[i + 1][mod];
                for (int r = mod + coins[i]; r <= Math.min(aim, zhangs[i] * coins[i]); r++) {
                    while (!minWindow.isEmpty() && (dp[i][minWindow.peekLast()] + compensate(minWindow.peekLast(), r, coins[i])) >= dp[i+1][r]){
                        minWindow.pollLast();
                    }
                    minWindow.addLast(r);
                    int overDue = r - coins[i] * (zhangs[i] +1);
                    //维持窗口
                    if (minWindow.peekFirst()  ==  overDue){
                        minWindow.pollFirst();
                    }
                    if (dp[i][minWindow.peekFirst()] == Integer.MAX_VALUE){
                        dp[i][r] = Integer.MAX_VALUE;
                    }else{
                        dp[i][r] = dp[i][minWindow.peekFirst()] + compensate(minWindow.peekFirst(), r, coins[i]);
                    }
                }

            }

        }
        return dp[0][aim];
    }

    private static int compensate(Integer integer, int r, int coin) {
        return 0;
    }
}
