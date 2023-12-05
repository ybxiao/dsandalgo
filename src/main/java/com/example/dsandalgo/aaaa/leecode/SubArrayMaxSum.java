package com.example.dsandalgo.aaaa.leecode;

// 本题测试链接 : https://leetcode.com/problems/maximum-subarray/
public class SubArrayMaxSum {

    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur = cur + arr[i];
            max = Math.max(max, cur);
            cur = Math.max(0, cur);
        }
        return max;
    }

    public static int maxSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int pre = arr[0];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            int cur = Math.max(arr[i], pre + arr[i]);
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;
    }

}
