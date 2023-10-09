package com.example.dsandalgo.aaaa.basic;

public class TrappingRainWater {

    public static int trap(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int leftMax = arr[0];
        int rightMax = arr[n - 1];
        int leftIndex = 1;
        int rightIndex = n - 1;

        int ans = 0;
        while (leftIndex <= rightIndex) {
            if (leftMax >= rightMax) {
                ans += Math.max(0, rightMax - arr[rightIndex]);
                rightMax = Math.max(rightMax, arr[rightIndex]);

            } else {
                ans += Math.max(0, leftMax - arr[leftIndex]);
                leftMax = Math.max(leftMax, arr[leftIndex]);

            }

        }


        return ans;
    }
}
