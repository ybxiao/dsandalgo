package com.example.dsandalgo.foundationclass.class12;

/**
 *
 * 给定数组arr，arr中所有的值都为正数且不重复
 * 每个值代表一种面值的货币，每种面值的货币可以使用任意张
 * 再给定一个整数 aim，代表要找的钱数
 * 求组成 aim 的方法数
 *
 *
 *
 */
public class Code09_CoinsWay {

    //arr中都是正数，返回组成aim的方法数
    public static int ways1(int[] arr,int aim){
        if (arr.length == 0 || arr == null || aim ==0 ){
            return 0;
        }

        return process1(arr,0,aim);

    }

    //0..index上已经决策完毕
    //在arr[index ...]的位置上的钱币可以随意使用，组合成rest的方法数
    private static int process1(int[] arr, int index, int rest) {
        if (rest <  0){
            return 0;
        }
        if (index == arr.length){
            return rest == 0? 1 : 0;
        }
        int ans = 0;
        for (int i = 0; arr[index] * i <= rest; i++) {
            ans += process1(arr,index+1,rest-arr[index]*i);
        }
        return ans;

    }

    //记忆话搜索 傻缓存
    private static int ways2(int[] arr,int aim){
        if (arr.length == 0 || arr == null || aim ==0 ){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = -1 ;
            }
        }
        return process2(arr,0,aim,dp);

    }

    private static int process2(int[] arr,int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1){
            return dp[index][rest];
        }
        if (index == arr.length){
            dp[index][rest] = rest == 0 ? 1:0;
            return dp[index][rest];
        }

        int ways = 0;
        for (int j = 0; arr[index] * j <= rest; j++) {
            ways += process2(arr,index+1,rest-arr[index]*j,dp);
        }
        dp[index][rest]  = ways;

        return ways;
    }

    // 标准动态规划
    private static int ways3(int[] arr, int aim){
        if (arr.length == 0 || arr == null || aim == 0 ){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0]  = 1;
        for (int i = n-1 ; i >=0 ; i-- ) {
            for (int j = 0; j <= aim; j++) {
                int ans = 0;
                for (int k = 0; arr[i] * k <= j; k++) {
                    ans += dp[i+1][j-arr[i]*k];
                }
                dp[i][j]  = ans;
            }
        }

        return dp[0][aim];

    }

    public static int ways4(int[] arr, int aim){
        if (arr.length == 0 || arr == null || aim ==0 ){
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0]  = 1;
        for (int i = 0; i < n ; i++) {
            dp[i][0]= 1;
        }
        for (int i = n-1 ; i >=0 ; i-- ) {
            for (int j = 1; j <= aim; j++) {
               dp[i][j] =  dp[i+1][j];
               if (j - arr[i] >= 0 ){
                   dp[i][j] += dp[i][j-arr[i]];
               }
            }
        }

        return dp[0][aim];



    }

    public static void main(String[] args) {
        int[] arr = { 5, 10,50,100 };
        int sum = 1000;
        System.out.println(ways1(arr, sum));
        System.out.println(ways2(arr, sum));
        System.out.println(ways3(arr, sum));
        System.out.println(ways4(arr, sum));
    }
}
