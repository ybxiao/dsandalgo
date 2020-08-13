package com.example.dsandalgo.class11;

/**
 * created on 2020/8/6.
 * time: 17:19
 * 背包问题
 *
 *
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 *
 *
 * @author yibo.xiao
 */


public class Code07_Knapsack {

    //暴力求解主函数
    public static int getMaxValue(int[] w,int[] v, int bags){

        return  process1(w,v,0,0,bags);


    }

    //0..index-1已经决策完毕
    //在index上进行决策
    //alwayW 表示决策的货物重量
    //bags 是背包的可承重
    //函数返回值表示index上之后的决策带来的价值收益
    private static int process1(int[] w, int[] v, int index, int alwaysW, int bags) {
        if (alwaysW > bags){
            return -1;
        }
        if (index == w.length){
            return 0;
        }
        int p1 = process1(w, v, index + 1, alwaysW, bags);

        //挑选index位置上的货物
        int casePick = process1(w, v, index + 1, alwaysW + w[index], bags);
        int p2 =-1;
        if (casePick !=-1){
            p2 = casePick + v[index];
        }
        return Math.max(p1,p2);


    }


    //0..index-1已经决策完毕
    //在index上进行决策
    //rest表示剩余的背包容量
    //函数返回值表示index上之后的决策带来的价值收益
    // 返回-1 没有方案

    private static int process2(int[] w, int[] v, int index, int rest) {

        if (rest <= 0){
            return -1;
        }
        if (index == w.length){
            return 0;
        }
        int p1 = process2(w, v, index + 1,rest);

        //挑选index位置上的货物
        int casePick = process2(w, v, index + 1, rest - w[index]);
        int p2 =-1;
        if (casePick !=-1){
            p2 = casePick + v[index];
        }
        return Math.max(p1,p2);


    }


    /**
     * 动态规化方法
     * dp[0][bags]
     * @param w
     * @param v
     * @param bags
     * @return
     */
    private static int dpWay(int[] w,int[] v, int bags){
        int N =  w.length;
        int[][] dp = new int[N+1][bags+1];

        for (int i = N-1; i >=0 ; i--) {
            for (int j = 1; j < bags; j++) {

                //dp[i][j] = ?;

                int p1 =  dp[i+1][j];
                int p2 = -1;
                if (w[j] <= bags){
                    p2 = v[i] + dp[i+1][bags-w[j]];
                }
                dp[i][j] =Math.max(p1,p2);
            }


        }

        return dp[0][bags];



    }

    public static void main(String[] args) {
        int[] w = new int[]{1,2,3};
        int[] v = new int[]{2,3,4};
        int bags = 6;

        getMaxValue(w,v,bags    );
    }


}
