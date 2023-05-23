package com.example.dsandalgo.aaaa.basic;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 */
public class AllLessNumSubArray {
    /***
     * 暴力尝试方法
     * @param arr
     * @param sum
     * @return
     */
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                int min = arr[i];
                int max = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                    max = Math.max(max, arr[k]);

                }
                if (max - min <= sum) {
                    count++;
                }


            }
        }


        return count;


    }

    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        LinkedList<Integer> maxWindow = new LinkedList<Integer>();
        LinkedList<Integer> minWindow = new LinkedList<Integer>();
        int length = arr.length;
        int right = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            while (right < length) {
                while (!maxWindow.isEmpty() && arr[maxWindow.peekFirst()] <= arr[right]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(right);

                while (!minWindow.isEmpty() && arr[minWindow.peekFirst()] >= arr[right]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(right);

                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    right++;
                }

            }
            count += right - i;
            if (maxWindow.peekFirst() == i) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == i) {
                minWindow.pollFirst();
            }

        }
        return count;
    }


    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] +" ");
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        int maxValue  = 10;
        int maxLen = 5;
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum =  (int)((Math.random() * (maxValue  +1)));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2){
                System.out.println("ans1: " +  ans1);
                System.out.println("ans2: " +  ans2);
                printArray(arr);
                System.out.println("sum: "+ sum);
                System.out.println("oops");
                break;
            }

        }
        System.out.println("finish");

    }


}
