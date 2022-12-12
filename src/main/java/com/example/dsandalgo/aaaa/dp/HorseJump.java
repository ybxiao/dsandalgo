package com.example.dsandalgo.aaaa.dp;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class HorseJump {
    public static int jump(int x, int y, int k) {
        return process(0, 0, x, y, k);
    }

    /**
     * @param a    当前马所在的横坐标位置
     * @param b    当前马所在的纵坐标位置
     * @param x    最终要到的横坐标位置
     * @param y    最总要到的纵坐标位置
     * @param rest 剩余的部署
     * @return 从（a,b）跳rest步 到(x,y)的方法数。
     */
    private static int process(int a, int b, int x, int y, int rest) {
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        if (rest == 0) {
            return (a == x && b == y) ? 1 : 0;
        }
        int ways = process(a + 1, b + 2, x, y, rest - 1);
        ways += process(a + 2, b + 1, x, y, rest - 1);
        ways += process(a + 2, b - 1, x, y, rest - 1);
        ways += process(a + 1, b - 2, x, y, rest - 1);
        ways += process(a - 1, b - 2, x, y, rest - 1);
        ways += process(a - 2, b - 1, x, y, rest - 1);
        ways += process(a - 2, b + 1, x, y, rest - 1);
        ways += process(a - 1, b + 2, x, y, rest - 1);
        return ways;
    }

    //通过位置依赖，来进行dp表的赋值
    private static int dpWays(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[x][y][0] = 1;
        //遍历填格子的方式，很重要
        for (int z = 1; z <=k ; z++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][z] = pick(dp, i + 1, j + 2, z - 1);
                    dp[i][j][z] += pick(dp, i + 2, j + 1, z - 1);
                    dp[i][j][z] += pick(dp, i + 2, j - 1, z - 1);
                    dp[i][j][z] += pick(dp, i + 1, j - 2, z - 1);
                    dp[i][j][z] += pick(dp, i - 1, j - 2, z - 1);
                    dp[i][j][z] += pick(dp, i - 2, j - 1, z - 1);
                    dp[i][j][z] += pick(dp, i - 2, j + 1, z - 1);
                    dp[i][j][z] += pick(dp, i - 1, j + 2, z - 1);


                }

            }
        }

        return dp[0][0][k];
    }

    private static int pick(int[][][] dp, int i, int j, int k) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][k];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int k = 10;
        System.out.println(jump(x, y, k));
        System.out.println(dpWays(x, y, k));
    }

}
