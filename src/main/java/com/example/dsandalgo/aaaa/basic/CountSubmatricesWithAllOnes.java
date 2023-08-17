package com.example.dsandalgo.aaaa.basic;

import java.util.Stack;

/**
 * 测试链接：https://leetcode.com/problems/count-submatrices-with-all-ones
 */
public class CountSubmatricesWithAllOnes {
    public static int numSummat(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int nums = 0;
        int[] height = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                height[i] = (matrix[i][j] == 1) ? height[i]++ : 0;
            }
            nums += countFromBottom(height);

        }


        return nums;
    }

    /**
     * 求一个数组中i位置，左右两边离i最近的小于arr[i]的数。
     * @param arr
     * @return
     */
    private static int countFromBottom(int[] arr) {
        if (arr == null || arr.length == 0){
            return 0;
        }
        int nums = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                Integer pop = stack.pop();

                if (arr[pop] > arr[i]){
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    int n = i - pop -1;
                    int down = left == -1 ? 0 : Math.max(arr[left], arr[i]);
                    nums += (arr[pop] -arr[down]) * nums(n);
                }


            }
            stack.push(i);

        }

        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int n = arr.length - left -1;
            int down = left == -1 ? -1 : arr[left];
            nums += (arr[pop] -arr[down]) * nums(n);
        }

        return nums;
    }

    private static int nums(int n) {
        return (n * (n+1)) >>1;
    }

}
