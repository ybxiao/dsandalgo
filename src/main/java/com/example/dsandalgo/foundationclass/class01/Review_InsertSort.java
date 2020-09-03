package com.example.dsandalgo.foundationclass.class01;

public class Review_InsertSort {

    //插入排序，从0到index位置上逐步变有序
    public void insertSort(int[] nums){

        if (nums == null  || nums.length == 0){
            return;
        }

        for (int i = 1; i < nums.length ; i++) {
            for (int j = i; j >=0     ; j--) {
                if (nums[j] < nums[j-1]){
                    swap(nums,j-1,j);
                }
            }



        }

    }

    private void swap(int[] nums, int j, int i) {
    }
}
