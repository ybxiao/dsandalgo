package com.example.dsandalgo.aaaa.basic;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/count-submatrices-with-all-ones
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 */
public class MaximalRectangleCopy2023 {
    public static int maximalRectangle(int[][] mat) {
        if (mat == null || mat.length <= 0 || mat[0].length <= 0) {
            return 0;
        }
        int[] height = new int[mat[0].length];
        int maxArea = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[i] = mat[i][j] == '0' ? 0 : height[i] + 1;
            }
            maxArea = Math.max(maxArea, maxRecFromBottom(height));
        }
        return maxArea;

    }

    //直方图问题
    public static int maxRecFromBottom(int[] height) {

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, (i - left - 1) * height[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int right = height.length;
            int left = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, (right - left - 1) * height[pop]);
        }
        return maxArea;


    }
}
