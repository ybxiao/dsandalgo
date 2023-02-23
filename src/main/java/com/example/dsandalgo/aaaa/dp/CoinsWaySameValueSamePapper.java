package com.example.dsandalgo.aaaa.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * * 每个值都认为是一张货币，
 * * 认为值相同的货币没有任何不同，
 * * 返回组成aim的方法数
 * * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * * 方法：1+1+1+1、1+1+2、2+2
 * * 一共就3种方法，所以返回3
 */
public class CoinsWaySameValueSamePapper {
    //对数组进行词频统计，计算出每种货币的张数
    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] coins, int[] zhangs) {
            this.coins = coins;
            this.zhangs = zhangs;
        }
    }

    public static Info getInfo(int[] nums) {
        HashMap<Integer, Integer> coinCounts = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (coinCounts.containsKey(nums[i])) {
                Integer integer = coinCounts.get(nums[i]);
                coinCounts.put(nums[i], integer + 1);
            } else {
                coinCounts.put(nums[i], 1);
            }
        }
        int n = coinCounts.size();
        int[] coins = new int[n];
        int[] zhangs = new int[n];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : coinCounts.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            coins[index] = key;
            zhangs[index++] = value;
        }
        return new Info(coins, zhangs);
    }

    public static int coinsWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        return process(coins, zhangs, 0, aim);

    }

    /**
     * @param coins  货币数组
     * @param zhangs 货币张数
     * @param index
     * @param rest   0...index 已经自由选择货币完毕，此时还剩下rest钱数需要后续选择完成
     * @return 从index...coins.length上 自由选择货币，总数不大于aim的方法数
     */
    private static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        //index位置的货币可以使用0...zhangs[index] 这么多张
        for (int i = 0; i * coins[index] <= rest & i <= zhangs[index]; i++) {
            ways += process(coins, zhangs, index + 1, rest - i * coins[index]);
        }
        return ways;
    }


    //todo 改造成动态规划的方法
    //dp[i][j] 代表的含义：coins[i...n]位置上的货币自由选择，凑成钱数j的方法数
    //
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int n = coins.length;
        int[][] dp = new int[n+1][aim + 1];
        dp[n][0] = 1;
        for (int i = n -1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = 0;
                for (int k = 0; k <= zhangs[i] && k * coins[i] <= j; k++) {
                    dp[i][j] += dp[i + 1][j - k * coins[i]];
                }

            }

        }
        return dp[0][aim];

    }

    //严格位置依赖的方法优化，替代掉枚举字段类型
    public static int dp2(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int n = coins.length;
        int[][] dp = new int[n+1][aim + 1];
        dp[n][0] = 1;
        //从下往上，从左往右依次去填dp[i][j]的值
        for (int i = n-1; i >=0 ; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i+1][rest] ;
                if (rest - coins[i] >= 0){
                    dp[i][rest] += dp[i][rest - coins[i]];
                }
                if (rest - coins[i] * (zhangs[i] + 1) >=0){
                    dp[i][rest] -= dp[i+1][rest - coins[i] * (zhangs[i] + 1) ];
                }

            }

        }
        return dp[0][aim];

    }

    public static int[] randomArray(int maxLen ,int maxValue){
        int n = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int v = (int) (Math.random() * maxValue) + 1;
            arr[i] = v;
        }
        return arr;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue =20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int)(Math.random() * maxValue) +1;
            int ans1 = coinsWays(arr, aim);
            int ans2 = dp2(arr, aim);
            int ans3 = dp1(arr, aim);
            if (ans1 != ans2 || ans2 != ans3){
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
            
        }

        System.out.println("测试结束");
    }


}
