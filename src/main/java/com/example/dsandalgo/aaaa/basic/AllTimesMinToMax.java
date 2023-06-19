package com.example.dsandalgo.aaaa.basic;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class AllTimesMinToMax {

    //暴力解，枚举每一个子数据，求指标，答案必在其中
    public static int max1(int[] arr){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = arr[i];
                int sum  = 0;
                for (int k = i; k <= i; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, sum * minNum);

            }
        }
        return max;
    }

    //使用单调栈
    public static int max2(int[] arr){
        Stack<Integer>  stack = new Stack<>();
        int size = arr.length;
        int[] sum = new int[size];
        sum[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sum[i] = sum[i-1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            //临界值
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                Integer j = stack.pop();
                int cur = (stack.isEmpty() ? sum[i-1] : (sum[i-1] - sum[stack.peek()])) * arr[j];
                max = Math.max(max, cur);
            }

            stack.push(i);

        }
        while (!stack.isEmpty()){
            Integer k = stack.pop();
            int cur = (stack.isEmpty() ? sum[size-1] : (sum[size-1] - sum[stack.peek()])) * arr[k];
            max = Math.max(max, cur);
        }
        return max;
    }

}
