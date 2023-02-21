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
        for (int i = 0; i * coins[index] <= rest & i < zhangs[index]; i++) {
            ways += process(coins, zhangs, index + 1, rest - i * coins[index]);
        }
        return ways;
    }


    //todo 改造成动态规划的方法

}
