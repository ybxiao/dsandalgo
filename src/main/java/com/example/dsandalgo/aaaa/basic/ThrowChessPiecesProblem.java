package com.example.dsandalgo.aaaa.basic;

/**
 * 一座大楼有0~N层，地面算作第0层，最高的一层为第N层
 * 已知棋子从第0层掉落肯定不会摔碎，从第i层掉落可能会摔碎，也可能不会摔碎(1≤i≤N)
 * 给定整数N作为楼层数，再给定整数K作为棋子数
 * 返回如果想找到棋子不会摔碎的最高层数，即使在最差的情况下扔的最少次数
 * 一次只能扔一个棋子
 * N=10，K=1
 * 返回10
 * 因为只有1棵棋子，所以不得不从第1层开始一直试到第10层
 * 在最差的情况下，即第10层是不会摔坏的最高层，最少也要扔10次
 * N=3，K=2
 * 返回2
 * 先在2层扔1棵棋子，如果碎了试第1层，如果没碎试第3层
 * N=105，K=2
 * 返回14
 * 第一个棋子先在14层扔，碎了则用仅存的一个棋子试1~13
 * 若没碎，第一个棋子继续在27层扔，碎了则用仅存的一个棋子试15~26
 * 若没碎，第一个棋子继续在39层扔，碎了则用仅存的一个棋子试28~38
 * 若没碎，第一个棋子继续在50层扔，碎了则用仅存的一个棋子试40~49
 * 若没碎，第一个棋子继续在60层扔，碎了则用仅存的一个棋子试51~59
 * 若没碎，第一个棋子继续在69层扔，碎了则用仅存的一个棋子试61~68
 * 若没碎，第一个棋子继续在77层扔，碎了则用仅存的一个棋子试70~76
 * 若没碎，第一个棋子继续在84层扔，碎了则用仅存的一个棋子试78~83
 * 若没碎，第一个棋子继续在90层扔，碎了则用仅存的一个棋子试85~89
 * 若没碎，第一个棋子继续在95层扔，碎了则用仅存的一个棋子试91~94
 * 若没碎，第一个棋子继续在99层扔，碎了则用仅存的一个棋子试96~98
 * 若没碎，第一个棋子继续在102层扔，碎了则用仅存的一个棋子试100、101
 * 若没碎，第一个棋子继续在104层扔，碎了则用仅存的一个棋子试103
 * 若没碎，第一个棋子继续在105层扔，若到这一步还没碎，那么105便是结果
 */
public class ThrowChessPiecesProblem {

    public static int superEggDrop1(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[1][i] = i;
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                int res = Integer.MAX_VALUE;
                for (int x = 1; x <= j; x++) {
                    res = Math.min(res, Math.max(dp[i - 1][x - 1], dp[i][j - x]) + 1);
                }
                dp[i][j] = res;
            }
        }
        return dp[k][n];
    }

    public static int superEggDrop2(int k, int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 2; i <= k; i++) {
            int[] temp = new int[n + 1];
            int x = 1;
            for (int j = 1; j <= n; j++) {
                while (x < j && Math.max(dp[x - 1], temp[j - x]) > Math.max(dp[x], temp[j - x - 1])) {
                    x++;
                }
                temp[j] = Math.max(dp[x - 1], temp[j - x]) + 1;
            }
            dp = temp;
        }
        return dp[n];

    }
    public static int superEggDrop3(int k, int n){
        int[] dp = new int[k + 1];
        int[] sum = new int[n + 1];
        int[] help = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            sum[i] = sum[i - 1] + dp[i];
        }
        for (int r = 2; r <= k; r++) {
            int M = n;
            for (int N = n; N > r; N--) {
                while (M > r && (sum[N] - sum[M - 1]) >= (sum[N - 1] - sum[M - 2])) {
                    M--;
                }
                help[N] = (sum[N] - sum[M - 1]) + dp[M - 1];
            }
            dp = help;
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i - 1] + dp[i];
            }
        }
        return dp[n];
    }

    public static int right(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        return process1(k ,n);
    }


    private static int process1(int k, int rest) {
        if (k == 1) {
            return rest;
        }
        if (rest == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i != rest + 1; i++) {
            ans = Math.min(ans, Math.max(process1(k - 1, i - 1), process1(k, rest - i)));
        }
        return ans;
    }
}
