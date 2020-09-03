package com.example.dsandalgo.foundationclass.class01;

public class Review_BubbleSort {
    //冒泡排序
    public void bubbleSort(int[] nums){
        if (nums == null  || nums.length == 0){
            return;
        }
        for (int i = nums.length-1; i >=0   ; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j+1]){
                    swap(nums,j,j+1);
                }
            }
        }




    }

    private void swap(int[] nums, int j, int i) {
    }
}
