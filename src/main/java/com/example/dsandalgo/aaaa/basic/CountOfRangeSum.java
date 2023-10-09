package com.example.dsandalgo.aaaa.basic;

/**
 * 数组区间和个数
 * <p>
 * https://leetcode.com/problems/count-of-range-sum/
 */
public class CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sums = new long[nums.length];
        //数组前缀和
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        return process(sums, 0, sums.length - 1, lower, upper);

    }

    //在数组sums[left...right] 范围上求区间和的个数
    private static int process(long[] sums, int left, int right, int lower, int upper) {
        //base case
        if (left == right) {
            return sums[left] >= lower && sums[left] <= upper ? 1 : 0;
        }
        int middle = left + (right - left) >> 2;

        return process(sums, left, middle, lower, upper) + process(sums, middle, right, lower, upper)
                + merge(sums, left, middle, right, lower, upper);
    }

    private static int merge(long[] sums, int left, int middle, int right, int lower, int upper) {
        int ans = 0;
        //利用窗口不回退的原理，每一次merge都是log(N)的复杂度
        //左闭右开[windowL, windowR)
        int windowL = left;
        int windowR = left;

        for (int i = middle; i <= right; i++) {
            long min = sums[i] - upper;
            long max = sums[i] - lower;
            while (windowL <= middle && sums[windowL] < min) {
                windowL++;
            }
            while (windowR <= middle && sums[windowR] <= max) {
                windowR++;
            }
            ans += windowR - windowL;

        }
        long[] help = new long[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = middle + 1;
        while (p1 <= middle && p2 <= right) {
            help[i++] = Math.min(sums[p1], sums[p2]);
        }
        while (p1 <= middle) {
            help[i++] = sums[p1++];
        }
        while (p2 <= right) {
            help[i++] = sums[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            sums[left + j] = help[j];
        }

        return ans;
    }
}
