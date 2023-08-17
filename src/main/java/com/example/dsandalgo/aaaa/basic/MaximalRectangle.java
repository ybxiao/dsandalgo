package com.example.dsandalgo.aaaa.basic;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/count-submatrices-with-all-ones
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 */
public class MaximalRectangle {
    public static int maximalRectangle(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int[] height = new int[mat.length];
        int maxArea = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                height[j] = (mat[i][j] == 0 ? 0 : height[j] + mat[i][j]);
                maxArea = Math.max(maxRecFromBottom(height), maxArea);
            }
        }
        return maxArea;

    }

    //直方图问题
    public static int maxRecFromBottom(int[] height) {
        int currArea = Integer.MIN_VALUE;

        Stack<Integer> stack = new Stack<>();
        int size = height.length;
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int j = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                currArea = Math.max((i - left - 1) * height[j], currArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer j = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            currArea = Math.max((size - left - 1) * height[j], currArea);
        }


        return currArea;
    }
}
