package com.example.dsandalgo.aaaa.dp;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class CoinsWayEveryPaperDifferent {
    //1 尝试方法（回溯算法）
    public static int coinsWay(int[] coins, int aim) {
        return process(coins, 0, aim);
    }

    // 从coins[index ...]中做选择，切好组成rest钱币的方法数
    public static int process(int[] coins, int index, int rest) {
        //base case
        if (rest < 0) {
            return 0;
        }
        //货币已经用完了,代表之前所做的选择，就是一种方法
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        //这种情况不同单独判断了
        /*if (rest == 0){
            return 1;
        }*/
        int p1 = process(coins, index + 1, rest - coins[index]);
        int p2 = process(coins, index + 1, rest);
        return p1 + p2;
    }

    //2 dp方法
    // dp[index][rest] 代表的含义是：从coin[index...]中做选择，恰好凑成rest金额的方法数
    public static int dp(int[] coins, int aim) {

        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] =1;
        for (int i = n-1; i >=0 ; i--   ) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] =  dp[i+1][j] + (j - coins[i] >=0 ?dp[i+1][j - coins[i]] :0);
            }

        }
        return dp[0][aim];
    }
    //对数器
    // 1. 构造一个生成任意数组的函数
    // 数组的构建要提前准备两个变量（一个是数组的最大值，和数组的次数）
    public static  int[] randomArray(int maxLen, int maxValue){
        int length  = (int)(Math.random() * maxLen) +1;
        int[] array = new int[length];
        for (int i = 0; i < length ; i++) {
            array[i] =  (int)(Math.random() * maxValue) +1;
        }
        return array;
    }


    public static void printArray(int[] arrs){
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(arrs[i]);
        }
    }

    public static void main(String[] args) {
        int maxLen  = 20;
        int maxValue= 200;
        int testTimes= 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] nums = randomArray(maxLen, maxValue);
            int aim = (int)(Math.random() * maxValue) +1;
            int ans1= coinsWay(nums, aim);
            int ans2 =  dp(nums, aim);
            if (ans1 !=  ans2){
                System.out.println("Oops!");
                //printArray(nums);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
