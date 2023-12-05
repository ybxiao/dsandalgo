package com.example.dsandalgo.aaaa.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSum {

    public List<List<Integer>> kSum(int[] nums, int target, int k) {
        Arrays.sort(nums);
        return kSum(nums, target, k, 0);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
        List<List<Integer>> res = new ArrayList<>();
        if (start >= nums.length) {
            return res;
        }
        if (k == 2) {
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> path = new ArrayList<>();
                    path.add(nums[left]);
                    path.add(nums[right]);
                    res.add(path);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        } else {
            for (int i = start; i < nums.length - k + 1; i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                List<List<Integer>> sub = kSum(nums, target - nums[i], k - 1, i + 1);
                for (List<Integer> arr : sub) {
                    arr.add(0, nums[i]);
                }
                res.addAll(sub);
            }
        }
        return res;
    }

}
