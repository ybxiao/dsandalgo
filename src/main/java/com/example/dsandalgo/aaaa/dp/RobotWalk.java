package com.example.dsandalgo.aaaa.dp;

import java.math.BigDecimal;

/**
 * 机器人走路问题
 * <p>
 * 题目：
 * <p>
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 *
 *
 *
 * 从左往右的尝试模型，根据暴力递归来找规律，从而找到动态转移方程
 * 这里的从左往右的尝试模型，并不是0...index位置的已经被选择，求在剩余的位置里面做选择的结果，而是直接假设任意位置 cur，从cur位置分别向左或者向右区尝试。
 */
public class RobotWalk {


    public static int ways1(int N, int M, int K, int P) {
        if (M > N || M < 1 || P > N || P < 1 || N < 2 || K < 1) {
            return -1;
        }

        return process1(M, K, P, N);

    }

    //机器人当前在cur位置，还剩rest步可走，想要最终到aim位置，返回一共有多少种方法
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(2, rest - 1, aim, N);
        }
        if (cur == N) {
            return process1(N - 1, rest - 1, aim, N);
        }

        int p1 = process1(cur + 1, rest - 1, aim, N);
        int p2 = process1(cur - 1, rest - 1, aim, N);
        return p1 + p2;
    }

    //记忆化搜素
    public static int ways2(int N ,int start, int k ,int aim){

        if (start > N || start < 1 || aim > N || aim < 1 || N < 2 || k < 1) {
            return -1;
        }

        int[][] dp = new int[N+1][k+1];
        for (int i = 0; i <=N ; i++) {
            for (int j = 0; j <=k; j++) {
                //使用-1 标记当前位置的值没有被更新过
                dp[i][j] = -1;
            }
        }
        return process2(start, k, aim, N, dp);
    }

    private static int process2(int cur, int rest, int aim, int n, int[][] dp) {
        if (dp[cur][rest] !=-1){
            return dp[cur][rest];
        }
        int ans;
        if (rest == 0) {
            ans =  cur == aim ? 1 : 0;
        }
        else if (cur == 1) {
            ans =  process1(2, rest - 1, aim, n);
        }
        else if (cur == n) {
            ans = process1(n - 1, rest - 1, aim, n);
        }
        else{
            int p1 = process1(cur + 1, rest - 1, aim, n);
            int p2 = process1(cur - 1, rest - 1, aim, n);
            ans =  p1 + p2;
        }


        dp[cur][rest] = ans;
        return ans;
    }

    //分析依赖关系
    // 可变参数列表 （cur,rest）
    public static int dp(int N, int start, int k, int aim){
        if (start > N || start < 1 || aim > N || aim < 1 || N < 2 || k <1 ) {
            return -1;
        }
        int[][] dp = new int[N+1][k+1];
        dp[aim][0] = 1;
        for (int i = 1; i <= k ; i++) {
            //对第一行进行赋值
            dp[1][i] = dp[2][i-1];
            for (int j = 2; j <N ; j++) {
                dp[j][i] = dp[j +1][i-1] + dp[j-1][i-1];
            }
            //对第N行进行赋值
            dp[N][i] = dp[N-1][i-1];
        }
        return dp[start][k];
    }


    public static void main(String[] args) {
        /*System.out.println(ways1(20,3,8,7));
        System.out.println(ways2(20,3,8,7));
        System.out.println(dp(20,3,8,7));*/
        int count  = 0;
        for (int i = 0; i < 10000; i++) {
            int i1 = (int) (Math.random() * 100 + 1);
            if (i1 == 100){
                count ++;
            }
        }
        System.out.println(count);
        /*long currentPrice = 123;
        long underLinedPrice = 456;
        BigDecimal currentPriceBigDecimal = new BigDecimal(currentPrice);
        BigDecimal underlinedPriceBigDecimal = new BigDecimal(underLinedPrice);
        System.out.println(currentPriceBigDecimal.divide(underlinedPriceBigDecimal, 2, BigDecimal.ROUND_CEILING));*/

    }

}
