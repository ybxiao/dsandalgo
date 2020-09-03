package com.example.dsandalgo.foundationclass.class01;

public class Review_BEExist {
    //二分法查找有序数组里面是否存在target数
    public boolean bexist(int[] arr,int num){
        if (arr == null  || arr.length == 0){
            return false;
        }
        int left = 0;
        int right = arr.length;
        while (left < right){
            int mid = left + (right-left)/2;
            if (arr[mid] == num){
                return true;
            }
            if (arr[mid] < num){
                left = mid +1;
            }
            else {
                right = mid -1;
            }


        }
        return false;

    }







}
