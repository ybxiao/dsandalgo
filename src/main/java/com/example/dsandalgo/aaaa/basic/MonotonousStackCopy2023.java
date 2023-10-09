package com.example.dsandalgo.aaaa.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈的原理（无重复数+有重复数） 用题目来学习单调栈提供的便利性
 */
public class MonotonousStackCopy2023 {

    // 给一个数组，返回数组中元素i，左右两边离它最近的比它大的数（或者离它最近比它小的数）
    // 单调栈，栈里的元素肯定是单调递增或者递减的。
    // 如果不用单调栈的话，就要对每一个位置进行左右遍历，来找出当前位置，左边和右边的信息O（N^2）
    public static int[][] rightWay(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int n = arr.length;
        int[][] res = new int[n][2];
        for (int i = 0; i < n; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int j = i - 1;
            while (j >= 0) {
                if (arr[i] > arr[j]) {
                    leftLessIndex = j;
                    break;
                }
                j--;
            }

            int k = i + 1;
            while (k < n) {
                if (arr[i] > arr[k]) {
                    rightLessIndex = k;
                    break;
                }
                k++;
            }

            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;

        }
        return res;
    }

    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        // stack 从小到大
        // stack里面放的是索引
        Stack<Integer> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer pop = stack.pop();
                res[pop][1] = i;
                res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            res[pop][1] = -1;
            res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
        }


        return res;
    }

    public static int[][] getNearLessRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int n = arr.length;
        int[][] res = new int[n][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() ) {
                if (arr[stack.peek().get(stack.peek().size()) - 1] > arr[i]){
                    List<Integer> pop = stack.pop();
                    for (Integer index : pop) {
                        res[index][1] = i;
                        res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    }
                }

            }
            if (arr[stack.peek().get(stack.peek().size()) - 1] == arr[i]) {
                stack.peek().add(i);
            }else{
                stack.push(new ArrayList<>(i));
            }

        }
        while (!stack.isEmpty()){
            List<Integer> pop = stack.pop();
            for (Integer index : pop) {
                res[index][1] = -1;
                res[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            }
        }



        return null;

    }


}
