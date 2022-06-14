package com.example.dsandalgo.foundationclass.class01;

public class Review_SelectionSort {


    //选择排序 每次选出一个最小的，放到该放的位置上
    public void selectionSort(int [] nums){
        if (nums == null  || nums.length ==0){
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int minIndex = 0;
            for (int j = i; j < nums.length ; j++) {
                if (nums[j] > nums[j+1]){
                    minIndex = j+1;
                }

            }
            swap(nums,i,minIndex);
        }


    }

    public void selectionSortV2(int[] nums){
        if (nums == null  || nums.length ==0){
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int minIndex = 0;
            for (int j = i; j < nums.length ; j++) {
                if (nums[j] <= nums[j+1]  ){
                    if (nums[minIndex] < nums[j]){
                        minIndex = j;
                    }

                }else{
                    if (nums[minIndex] < nums[j+1]){
                        minIndex = j+1;
                    }
                }
            }
            swap(nums, i ,minIndex);
        }

    }

    private void swap(int[] nums, int i, int index) {
    }
}
