package com.example.dsandalgo.class01;

/**
 * 找局部极小值
 */
public class BSAwesome{

    public  int bsAwesome(int[] sortedArr){
        if (sortedArr ==null || sortedArr.length ==0){
            return  -1;
        }
        if (sortedArr.length ==1) {
            return 0;
        }
        if (sortedArr[0] < sortedArr[1]){return 0;}
        if (sortedArr[sortedArr.length-1] < sortedArr[sortedArr.length-2]){return sortedArr.length-1;}
        int L  = 0;
        int R  = sortedArr.length -1;
        while (L <= R){
            int mid  = L  + (R-L) >>2;
            if (sortedArr[mid] > sortedArr[mid-1] ){
              R =mid -1;
            }
            else if (sortedArr[mid] > sortedArr[mid+1]){
                L = mid +1;

            }
            else return mid;
        }

        return  L;


    }

}
