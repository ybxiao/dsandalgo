package com.example.dsandalgo.foundationclass.class01;

public class SelectionSort {



    public void selectionSort(int [] nums){

        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < nums.length; j++) {
               /* if(nums[j] <  nums[index]){
                    index = j;
                }*/
                minIndex = nums[j] < nums[minIndex] ? j :minIndex;
                
            }
            swap(nums,i,minIndex);
        }


    }

    private void swap(int[] nums, int i, int index) {
    }
}
