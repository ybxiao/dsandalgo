package com.example.dsandalgo.class01;

public class BSNearRight {
    public int bsNearLeft(int[] sortedArr,int num){

        if (sortedArr == null  || sortedArr.length ==0){
            return -1;

        }
        int L  =  0;
        int R  = sortedArr.length -1;
        //记录最右侧小于等于的对号
        int index =-1;
        while (L <= R){
            int mid =  L  + (R-L) >>2;
            if (sortedArr[mid] <= num) {R = mid -1; index =mid;}
            else L =mid +1;

        }
        return index;

    }
}
