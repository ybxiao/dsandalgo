package com.example.dsandalgo.class01;

public class BubbleSort {
    public void bubbleSort(int[] nums){

        for (int i = nums.length-1; i >0; i--) {

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
