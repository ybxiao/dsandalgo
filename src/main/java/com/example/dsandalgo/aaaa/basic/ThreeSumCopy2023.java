package com.example.dsandalgo.aaaa.basic;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class ThreeSumCopy2023 {

    public static List<List<Integer>> threeSum(int[] arr, int target) {
        if (arr == null || arr.length <= 2) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];
                if (sum == target) {
                    res.add(Lists.newArrayList(i, left, right));
                    while (left < right && arr[right - 1] == arr[right]) {
                        right--;
                    }
                    while (left < right && arr[left + 1] == arr[left]) {
                        left++;
                    }
                    left++;
                    right--;
                } else if (target > sum) {
                    left++;
                } else {
                    right--;
                }

            }
        }

        return res;
    }
}
