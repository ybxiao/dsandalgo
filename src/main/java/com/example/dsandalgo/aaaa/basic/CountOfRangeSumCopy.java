package com.example.dsandalgo.aaaa.basic;

/**
 * 数组区间和个数
 * <p>
 * https://leetcode.com/problems/count-of-range-sum/
 */
public class CountOfRangeSumCopy {

    public static int countOfRangeSum(int[] nums, int lower, int higher) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //sums长度为n+1，因为包含一个什么都没有的累加和 0
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i+1] = sums[i] + nums[i];
        }

        return countWhileMergeSort(sums, 0, n + 1, lower, higher);

    }

    private static int countWhileMergeSort(long[] sums, int start, int end, int lower, int higher) {
        if (end - start <= 1) {
            return 0;
        }
        int mid = start + (start - end) >> 2;
        int count = countWhileMergeSort(sums, start, mid, lower, higher) +
                countWhileMergeSort(sums, mid + 1, end, lower, higher);
        int wL = mid;
        int wR = mid;
        int t = mid;
        long[] cache = new long[start - end + 1];
        for (int i = start, r = 0; i < mid; i++, r++) {
            while (wL < end && sums[wL] - sums[i] < lower) {
                wL++;
            }
            while (wR < end && sums[wR] - sums[i] <= higher) {
                wR++;
            }
            while (t < end && sums[r] < sums[t]) {
                cache[r++] = sums[t++];
            }
            cache[r] = sums[i];
            count += wR - wL;

        }

        System.arraycopy(cache, 0, sums, start, t - start);

        return count;

    }
}
