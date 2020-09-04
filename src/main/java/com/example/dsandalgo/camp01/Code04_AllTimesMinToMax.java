package com.example.dsandalgo.camp01;

import java.util.Stack;

/**
 * created on 2020/9/4.
 * time: 10:36
 *
 * 例题：
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */


public class Code04_AllTimesMinToMax {

    //暴力求解，求出所有的子数组
    //累加子数组的值
    //遍历求出子数组的最小值 相乘
    // 时间复杂度O(N^3)
    public static int max1(int[] arr){

        int max =  Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length ; j++) {
                //累加和
                int sum = 0;
                int minNum =  Integer.MAX_VALUE;
                for (int k = i ; k < j; k++) {
                    sum += arr[k];
                    minNum =  Math.min(minNum,arr[k]);
                }

            max = Math.max(max,sum * minNum);


            }
        }

        return max;

    }

    // 转化问题
    // 使用单调递归栈求解数组中每一个元素左侧和右侧距离该元素最近的且比他小的元素位置
    // 同时求解数组中每一个位置的累加和，用一次遍历数组完成
    // 把求解问题转化成在范围上呈单调性的简单求解
    // 在递归栈中元素弹出时 对问题进行求解


    public static int getMax2(int[] arr){

        //sum数组放到是从0...index位置上的所有的累加和
        int[] sum  = new int[arr.length];
        sum[0] =  arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i-1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {

            //此时i的位置是当前弹出元素右侧，第一个比该元素小的位置。
            //栈中的元素 上下挨着的表示当前元素左侧距离最近的比他小的元素和当前元素右侧距离最近的比当前小的元素。
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                Integer pop = stack.pop();
                //stack.isEmpty() ? sum[i] * arr[pop] : (sum[i] - arr[stack.peek() - 1]) * arr[pop];
                max = Math.max(max,stack.isEmpty() ? sum[i -1 ] : (sum[i-1] - sum[stack.peek()]) * arr[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer j = stack.pop();
            //stack.isEmpty() ? sum[i] * arr[j] : (sum[i] - arr[stack.peek() - 1]) * arr[j];
            max = Math.max(max,stack.isEmpty() ? sum[j -1] : (sum[j-1] - arr[stack.peek()]) * arr[j]);

        }





        return max;


    }

}
