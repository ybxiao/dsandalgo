package com.example.dsandalgo.aaaa.basic;

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
            mid = left + (right - left) >> 1;
            if (sortedArray[mid] == num){
                return true;
            }else if (sortedArray[mid] > num){
                right = mid -1;
            }else {
                left = mid +1;
            }
        }
        return false;
    }



}
