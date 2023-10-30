package com.example.dsandalgo.aaaa.basic;

/**
 * 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序，返回满足这一设定的子数组中最短的是多长
 */
public class FindUnsortedSubarrayCopy2023 {
    public static int findUnSortedSubarray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int right = 0;
        int rMax = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > rMax) {
                right = i;
            }
            rMax = Math.max(rMax, arr[i]);
        }
        int left = n - 1;
        int lMin = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] < lMin) {
                left = i;
            }
            lMin = Math.min(lMin, arr[i]);

        }
        return Math.max(0, right - left + 1);

    }

}
