package com.example.dsandalgo.foundationclass.class01;

public class BEExist {
    public boolean bexist(int[] arr,int num){
        if (arr  == null  || arr.length <1 ){
            return false;
        }
        int R = arr.length;
        int L= 0;
        while (L <  R){
            int mid = L + (R -L) /2;
            if (num == arr[mid]) return true;
            else if (num < arr[mid])  R = mid-1;
            else L = mid+1;

        }



    return false;

    }







}
