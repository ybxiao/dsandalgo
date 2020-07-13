package com.example.dsandalgo.class01;

public class BSNearLeft {

    public int bsNearLeft(int[] sortedArr,int num){
        if (sortedArr == null || sortedArr.length == 0){
            return 0;
        }
        int R = sortedArr.length -1;
        int L =0 ;
        int mid =0;
        //记录最左的对号
        int index = -1;
        while (L <= R){
            mid  = L  + (R -L  )>> 2;

            if (sortedArr[mid] < num)  L  = mid +1;
            else if (sortedArr[mid] >= num) {
                R = mid -1;
                index = mid;
            }

        }
        return sortedArr[0] >=num ? 0 :index;
    }
}
