package com.example.dsandalgo.aaaa.dp;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 * <p>
 * 暴力解法：枚举所有的组成aim的方式，取使用货币数最少得一种。
 */
public class MinCoinsNoLimit {

    public static int minCoins(int[] nums, int aim) {
        return process(nums, 0, aim);
    }

    /**
     * @param nums
     * @param index 在nums index...往后位置上的钱币自由做选择
     * @param rest  还剩余要组成的钱币
     * @return 在nums[index...]上货币自由做选择，返回组成正好组成rest钱币的最少货币数
     */
    private static int process(int[] nums, int index, int rest) {
        if (index == nums.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int ans = Integer.MAX_VALUE;
            //列举可能性，用第一张钱币用几张来列举所有的可能性
            for (int zhang = 0; zhang * nums[index] <= rest; zhang++) {
                int next = process(nums, index + 1, rest - zhang * nums[index]);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, next + zhang);
                }
            }
            return ans;
        }

    }

    public static int dp1(int[] nums, int aim) {
        int n = nums.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhangs = 0; zhangs * nums[index] <= rest; zhangs++) {
                    int next = Math.min(ans, dp[index + 1][rest - zhangs * nums[index]]);
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + zhangs);
                    }
                }
                dp[index][rest] = ans;
            }
        }

        return dp[0][aim];


    }

    //严格位置依赖的动态规划，省掉枚举行为，时间复杂度降一阶
    public static int dp2(int[] nums, int aim) {
        int n = nums.length;
        int[][] dp = new int[n + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = Integer.MAX_VALUE;
        }
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if ((rest - nums[index]) >= 0 && dp[index][rest - nums[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], (dp[index][rest - nums[index]] + 1));
                }

            }

        }
        return dp[0][aim];
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < len; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue);
            } while (has[arr[i]]);
            has[arr[i]] = true;

        }
        return arr;

    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("arr[" + i + "]" + i + ",");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 3;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int)(Math.random() * maxLen) +1;
            int[] nums = randomArray(n, maxValue);
            int aim  = (int)(Math.random() * maxValue) +1;
            int ans1 = minCoins(nums, aim);
            int ans2 = dp1(nums, aim);
            int ans3 = dp2(nums, aim);
            if (ans1 != ans2 || ans1 != ans3){
                System.out.println("oops");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                printArray(nums);
                System.out.println(aim);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
