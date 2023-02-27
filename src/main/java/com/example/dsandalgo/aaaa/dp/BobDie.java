package com.example.dsandalgo.aaaa.dp;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class BobDie {
    public static double livePosibility1(int N, int M, int row, int col, int k) {
        return (double) process(N, M, row, col, k) / Math.pow(4, k);
    }

    //Bob 当前在(row, col)位置上，还剩余rest步可走，返回走完rest步之后仍在表格中的方法数
    public static long process(int N, int M, int row, int col, int rest) {
        //不在表格中的话，方法为0
        //只要任意一步踏出棋盘，就代表方法数为0
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        //剩余的步数全部走完，且没有踏出棋盘，返回生存点1
        if (rest == 0) {
            return 1;
        }
        long p1 = process(N, M, row + 1, col, rest - 1);
        long p2 = process(N, M, row - 1, col, rest - 1);
        long p3 = process(N, M, row, col + 1, rest - 1);
        long p4 = process(N, M, row, col - 1, rest - 1);
        return p1 + p2 + p3 + p4;
    }

    //3 维的动态规划 可变参数列表 row  col k
    // row的变化范围 0 ...N
    // col的变化范围 0 ...M
    // rest的变化范围 0 ... k
    public static double liveProbablity(int N, int M, int row, int col, int k) {
        long[][][] dp = new long[N + 1][M + 1][k+1];
        //初始化dp
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][rest] += pick(N, M, i + 1, j, rest - 1, dp);
                    dp[i][j][rest] += pick(N, M, i - 1, j, rest - 1, dp);
                    dp[i][j][rest] += pick(N, M, i, j - 1, rest - 1, dp);
                    dp[i][j][rest] += pick(N, M, i, j + 1, rest - 1, dp);
                }
            }

        }

        return dp[row][col][k]/Math.pow(4, k);

    }

    private static long pick(int n, int m, int row, int col, int rest, long[][][] dp) {
        if (row < 0 || row == n || col < 0 || col == m) {
            return 0;
        }
        return dp[row][col][rest];
    }


    public static void main(String[] args) {
        System.out.println(livePosibility1(10,10,4,7,8));
        System.out.println(liveProbablity(10,10,4,7,8));
    }
}
