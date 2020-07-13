package com.example.dsandalgo.class01;

public class InsertSort {

    public void insertSort(int[] nums){
        if (nums == null || nums.length <2){
            return;
        }


        for (int i = 1; i < nums.length; i++) {
            for (int j = i-1; j >=0 ; j--) {
                //写的有问题 和i比较循环无法进行
                if (nums[j] > nums[j+1]){
                    swap(nums,j ,j+1);
                    
                }
            }
        }


    }

    private void swap(int[] nums, int j, int i) {
    }
}
