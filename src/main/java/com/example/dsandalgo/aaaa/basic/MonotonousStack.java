package com.example.dsandalgo.aaaa.basic;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈的原理（无重复数+有重复数） 用题目来学习单调栈提供的便利性
 */
public class MonotonousStack {

    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;

            int cur = i - 1;
            while (cur >= 0) {
                if (arr[i] > arr[cur]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;

            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[i] > arr[cur]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }

            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;

        }

        return res;
    }

    //数组arr无重复值
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer j = stack.pop();
                res[j][0] = stack.isEmpty() ? -1 : stack.peek();
                res[j][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            res[pop][1] = -1;
        }

        return res;
    }

    //数组arr有重复值
    public static int[][] getNearLess(int[] arr) {
        //定义结果数组
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> pop = stack.pop();

                int leftNearLess = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int j = 0; j < pop.size(); j++) {
                    res[j][0] = i;
                    res[j][1] = leftNearLess;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = Lists.newArrayList();
                list.add(i);
                stack.push(list);
            }

        }
        while (!stack.isEmpty()) {
            List<Integer> popped = stack.pop();
            int right = -1;
            int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int i = 0; i < popped.size(); i++) {
                res[i][0] = right;
                res[i][1] = left;
            }
        }
        return res;
    }

    public static int[] getRandomArrayNoRepeat(int size) {
        int[] res = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < size; i++) {
            res[i] = i;
        }
        for (int i = 0; i < size; i++) {
            int swapIndex = (int) (Math.random() * size);
            int temp = res[swapIndex];
            res[swapIndex] = res[i];
            res[i] = temp;
        }
        return res;

    }

    public static int[] getRandomArray(int size ,int max){
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((int) (Math.random() * max));
        }
        return arr;

    }
    public static boolean isEqual(int[][] res1, int[][] res2){
        if (res1.length != res2.length){
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] !=  res2[i][0] || res1[i][1] !=  res2[i][1]){
                return false;
            }

        }

        return true;
    }

    public static void printArray(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
