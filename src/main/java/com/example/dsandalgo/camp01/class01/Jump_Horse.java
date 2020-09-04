package com.example.dsandalgo.camp01.class01;

/**
 * created on 2020/9/4.
 * time: 14:32
 * 跳马问题
 * 一匹马从(0,0)跳了k步，最后来到(x,y)的方法
 * @author yibo.xiao
 */


public class Jump_Horse {

    //马从(0,0)位置触发，跳k步，最后来到(x,y)的方法数
    //递归
    public static int ways(int x,int y ,int k){
        return f(x,y,k);
    }

    //设计递归函数+ f(x-2,y-1,k-1)
    private static int f(int x, int y, int k) {
        //base case
        if (k == 0){
            return x == 0 && y == 0 ? 1 : 0;
        }
        //边界限制
        if (x < 0 || y < 0 || x > 9 || y > 8){
            return 0;
        }
        return f(x-2,y+1,k-1)
                + f(x-2,y-1,k-1)
                + f(x+1,y+2,k-1)
                + f(x+1,y-2,k-1)
                + f(x-1,y+2,k-1)
                + f(x-1,y-2,k-1)
                + f(x+2,y-1,k-1)
                + f(x+2,y+1,k-1);
    }


    //返回dp[x][y][k]
    //往dp里面赋值

    public static  int way2(int x,int y ,int k){
        int [][][] dp = new int[10][9][k+1];
        dp[0][0][0] = 1;


        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int l = 1; l < dp[i][j].length; l++) {
                    //边界无法准确控制，所以要把限制条件抽想到一个单独的函数里面
                    dp[i][j][l] = getValue(dp,i-2,j+1,l-1)
                            +getValue(dp,x-2,y-1,l-1)
                            +getValue(dp,x+1,y-2,l-1)
                            +getValue(dp,x+2,y+2,l-1)
                            +getValue(dp,x-1,y+2,l-1)
                            +getValue(dp,x-1,y-2,l-1)
                            +getValue(dp,x+2,y-1,l-1)
                            +getValue(dp,x+2,y+1,l-1);

                }

            }

        }


        return dp[x][y][k];
    }

    private static int getValue(int[][][] dp, int x, int y, int l) {
        //边界限制
        if (x < 0 || y < 0 || x > 9 || y > 8){
            return 0;
        }
        return dp[x][y][l];
    }

}
