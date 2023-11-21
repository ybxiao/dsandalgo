package com.example.dsandalgo.aaaa.basic;

import java.util.Arrays;

// 给定一个数组arr，代表每个人的能力值。再给定一个非负数k。
// 如果两个人能力差值正好为k，那么可以凑在一起比赛，一局比赛只有两个人
// 返回最多可以同时有多少场比赛
public class MaxPairNumber {

    public static int maxPairNum1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }
        return process(arr, 0, k);
    }

    private static int process(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i = 1; i < arr.length; i = i + 2) {
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                swap(arr, r, index);
                ans = Math.max(ans, process(arr, index + 1, k));
                swap(arr, r, index);

            }
        }

        return ans;
    }

    private static void swap(int[] arr, int r, int index) {

    }


    //贪心的解法
    public static int maxPairNum2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int L = 0;
        int R = 0;
        boolean[] usedR = new boolean[n];
        int ans = 0;
        while (L < n && R < n) {
            if (usedR[L]) {
                L++;
            } else if (L >= R) {
                R++;
            } else {
                int distance = arr[R] - arr[L];
                if (distance == k) {
                    ans++;
                    usedR[R++] = true;
                    L++;
                } else if (distance < k) {
                    R++;
                } else {
                    L++;
                }

            }
        }
        return ans;

    }
}
