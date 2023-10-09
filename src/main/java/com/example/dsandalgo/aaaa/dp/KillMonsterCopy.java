package com.example.dsandalgo.aaaa.dp;

/**
 * 斜率优化
 * <p>
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 * <p>
 * 分析：
 * 每次砍掉[0...M]这么多血量，总共要砍K刀，那么总共的砍法数是：Math.pow(（M +1）, k)
 * 计算存活的概率，只需要计算出砍玩K刀之后，能把怪兽砍死的方法数即可
 */
public class KillMonsterCopy {

    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }

        long all = (long) Math.pow(M + 1, K);
        long survive = process(N, K, M);

        return (double) survive / all;
    }

    // 还剩restHp这么个血量，还有restKill刀要砍，
    private static long process(int restHp, int restKill, int m) {
        if (restKill == 0) {
            return restHp > 0 ? 0 : 1;
        }
        if (restHp <= 0) {
            return (long) Math.pow(m + 1, restKill);
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res += process(restHp - i, restKill - 1, m);
        }
        return res;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        //dp[i][j]代表怪兽还剩i点血量，j刀可以砍，存活的方法数
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dp[i][0] = (long) Math.pow(M + 1, i);
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[j][i] = 0;
                for (int k = 0; k <= M; k++) {
                    if ((j - k) > 0) {
                        dp[j][i] = dp[j - k][i - 1];
                    } else {
                        dp[j][i] = (long) Math.pow(M + 1, i - 1);
                    }
                }


            }

        }

        return dp[N][K] / all;
    }

    //斜率优化的动态规划
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        //dp[i][j]代表怪兽还剩i点血量，j刀可以砍，存活的方法数
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dp[i][0] = (long) Math.pow(M + 1, i);
        }
        for (int restK = 1; restK <= K; restK++) {
            for (int restHp = 1; restHp <= N; restHp++) {
                dp[restHp][restK] = dp[restHp - 1][restK] + dp[restHp][restK - 1];
                if (restHp - M - 1 < 0) {
                    dp[restHp][restK] -= dp[0][restK - 1];
                } else {
                    dp[restHp][restK] -= dp[restHp - M - 1][restK - 1];
                }

            }

        }

        return (double) dp[N][K] / all;
    }
}
