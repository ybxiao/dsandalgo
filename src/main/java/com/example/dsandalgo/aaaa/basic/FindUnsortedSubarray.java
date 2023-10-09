package com.example.dsandalgo.aaaa.basic;

/**
 * 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序，返回满足这一设定的子数组中最短的是多长
 */
public class FindUnsortedSubarray {
    public static int findUnSortedSubarray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int n = arr.length;
        int right = 0;
        //左边部分的最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (max > arr[i]) {
                right = i;
            }
            max = Math.max(max, arr[i]);
        }
        int left = n;
        //跟踪右边部分的最小值
        int min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (min < arr[i]) {
                left = i;
            }
            min = Math.min(min, arr[i]);

        }

        return Math.max(0, (left - right + 1));

    }

}
