package com.example.dsandalgo.aaaa.basic;

import java.util.Stack;

/**
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 * https://leetcode.com/problems/largest-rectangle-in-histogram
 */
public class LargestRectangleInHistogram {

    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int size = height.length;
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int j = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max((i - left - 1) * height[j], maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {

            int j = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max((size - left - 1) * height[j], maxArea);
        }
        return maxArea;

    }


    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int size = height.length;
        //用数组模拟栈
        int[] stack = new int[size];
        int maxArea = Integer.MIN_VALUE;
        // si模拟栈顶指正
        int si = -1;
        for (int i = 0; i < size; i++) {
            while (si != -1 && height[stack[si]] >= height[i]) {
                int j = stack[si--];
                int right = si == -1 ? -1 : stack[si];
                maxArea = Math.max((i - right - 1) * height[j], maxArea);
            }
            stack[++si] = i;
        }
        while (si != -1){
            int j = stack[si--];
            int right = si == -1 ? -1 : stack[si];
            maxArea = Math.max((size - right - 1) * height[j], maxArea);

        }

        return maxArea;



    }
}
