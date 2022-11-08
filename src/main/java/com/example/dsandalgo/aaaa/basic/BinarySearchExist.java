package com.example.dsandalgo.aaaa.basic;

import java.util.Arrays;

public class BinarySearchExist {
    /**
     * 在一个有序数组中，找到num的数
     * @param sortedArray
     * @param num
     * @return
     */
    public static boolean BSExist(int[] sortedArray, int num){
        if (sortedArray==null || sortedArray.length < 1){
            return false;
        }
        int left = 0;
        int right = sortedArray.length -1;
        int mid = 0;
        while (left < right){
            mid = left + ((right - left) >> 1);
            if (sortedArray[mid] == num){
                return true;
            }else if (sortedArray[mid] > num){
                right = mid -1;
            }else {
                left = mid +1;
            }
        }
        return sortedArray[left] == num;
    }

    public static boolean test(int[] sortedArray ,int num){
        if (sortedArray==null || sortedArray.length < 1){
            return false;
        }
        for (int i = 0; i < sortedArray.length; i++) {
            if (sortedArray[i] == num){
                return true;
            }
        }
        return false;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random() -  (maxValue * Math.random()));
        }

        return arr;
    }


    public static void main(String[] args) {

        /*int testTimes = 50000;
        int maxValue = 100;
        int maxSize = 10;
        boolean succeed = true;

        for (int i = 0; i < testTimes; i++) {
            int[] arr =  generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random() -  (maxValue * Math.random()));
            if (test(arr, value) != BSExist(arr, value)){
                succeed = false;
            }
        }

        System.out.println(succeed ? "Nice!" : "Fucking fucked!");*/


        System.out.println( 1+2 >>1);
        System.out.println( 1+ (2 >>1));

    }








}
