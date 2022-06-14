package com.example.dsandalgo.foundationclass.class01;

public class BubbleSort {
    public void bubbleSort(int[] nums){
        //外层主要是控制循环，先把最大的数沉底
        for (int i = nums.length-1; i >0; i--) {

            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j+1]){
                    swap(nums,j,j+1);

                }


            }
        }


    }

    /**
     * maopao
     * @param nums
     */
    public void bubbleSortV2(int[] nums){



    }

    private void swap(int[] nums, int j, int i) {
    }
}
