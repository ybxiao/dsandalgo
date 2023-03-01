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
public class KillMonster {
    public static double right(int N, int K, int M) {
        if (N < 1 || K < 1 || M < 1) {
            return 0;
        }
        long all = (long) Math.pow((M + 1), K);
        long survive = process(N, K, M);
        return (double) survive / all;

    }

    /**
     * @param restHp 当前剩余的血量
     * @param restK  还剩余的刀数
     * @param m      每次砍掉的血量范围0...m
     * @return restK刀内，能把怪兽砍死的方法数
     */
    private static long process(int restHp, int restK, int m) {
        if (restK == 0) {
            return restHp > 0 ? 0 : 1;
        }
        if (restHp <= 0) {
            return (long) Math.pow((m + 1), restK);
        }
        int ways = 0;
        //列举所有的可能性，当前这刀砍下去有m +1种可能性
        for (int i = 0; i <= m; i++) {
            ways += process(restHp - i, restK - 1, m);
        }

        return ways;
    }

    //动态规划的方法
    public static double dp1(int N, int K, int M) {
        //无效参数拦截
        if (N < 1 || K < 1 || M < 1) {
            return 0;
        }
        long all = (long) Math.pow((M + 1), K);
        //dp[i][j]代表的含义是 怪兽还剩下i点血量，使用j到能把怪兽砍死的方法数
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dp[0][i] = (long) Math.pow((M + 1), i);
        }
        for (int restK = 1; restK <= K; restK++) {
            for (int i = 1; i <= N; i++) {
                dp[i][restK] = 0;
                for (int j = 0; j <= M; j++) {
                    if (i - j >= 0) {
                        dp[i][restK] += dp[i - j][restK - 1];
                    } else {
                        dp[i][restK] += Math.pow((M + 1), restK - 1);
                    }

                }

            }

        }
        long right = dp[N][K];
        return (double) right / all;
    }

    //带斜率优化的动态方法
    //通过斜率优化的技巧，省掉枚举行为
    public static double dp2(int N, int K, int M) {
        //无效参数拦截
        if (N < 1 || K < 1 || M < 1) {
            return 0;
        }
        long all = (long) Math.pow((M + 1), K);
        //dp[i][j]代表的含义是 怪兽还剩下i点血量，使用j到能把怪兽砍死的方法数
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= K; i++) {
            dp[0][i] = (long) Math.pow((M + 1), i);
        }

        for (int times = 1; times <= K; times++) {
            for (int hp = 1; hp <= N; hp++) {
                dp[hp][times] = dp[hp][times - 1] + dp[hp - 1][times];
                if (hp - M - 1 >= 0) {
                    dp[hp][times] -= dp[hp - M - 1][times - 1];
                } else {
                    dp[hp][times] -= dp[0][times - 1];
                }

            }
        }


        return (double)dp[N][K]/all;
    }


    public static void main(String[] args) {
        int NMax = 10;
        int MMax=10;
        int kMax = 10;
        int testTimes =100;
        for (int i = 0; i < testTimes; i++) {
            int N = (int)(Math.random() * NMax);
            int M = (int)(Math.random() * MMax);
            int K = (int)(Math.random() * kMax);

            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            System.out.println("N,M,K" +N+"_"+M+"_"+K);
            if (ans1 != ans2 || ans1 != ans3){

                System.out.println("ans1= " + ans1);
                System.out.println("ans2= " + ans2);
                System.out.println("ans3= " + ans3);
                System.out.println("Oops!");
                break;
            }

        }

    }
}
