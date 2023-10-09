package com.example.dsandalgo.aaaa.basic;

/**
 * 返回一个数组中子数组最大累加和
 */
public class SubArrayMaxSum {

    public static int maxSum(int[] sum) {
        if (sum == null || sum.length == 0) {
            return -1;
        }
        int cur = 0;
        int max= 0 ;
        for (int i = 0; i < sum.length; i++) {
            cur = cur + sum[i];
            max = Math.max(cur, max);
            cur = Math.max(cur, 0);
        }
        return cur;
    }
}
