package com.example.dsandalgo.foundationclass.class05;

public class Code04_RadixSort {

    public void radixSort(int[] arr){
        if (arr == null || arr.length <2){
            return;
        }

        radixSort(arr,0,arr.length-1);
    }
    public void radixSort(int[] arr, int L ,int R){

        int[] count = new int[10];
        int[] help = new int[R-L+1];
        int m = 0;
        int n = 0;
        for (int i = 0; i < getMaxBit(arr); i++) {
            for (m = 0; m <=R ; m++) {
                int bit = getBit(arr[m], i);
                count[bit] ++;

            }
            for (int k  = 1; k < count.length; k++) {
                count[k] = count[k] + count[k-1];
            }


            for (m =R   ; m >=L  ; m--) {
                int bit = getBit(arr[m], i);
                help[count[bit] -1 ] = arr[m];
                count[bit] --;
            }
            for ( m =L,n = 0; m <= R; m++,n++) {
                arr[m] = help[n];
            }
        }



    }

    public int getBit(int v,int i){
        int  pow =(int) Math.pow(10, i);
        return (v/pow)%10;

    }

    public int getMaxBit(int[] arr){
        int maxbit = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int j = 0;
            int k = arr[i];
            while (k   >0){
                j ++;
                k = arr[i]/10;

            }
            maxbit = Math.max(j,maxbit);


        }
        return maxbit;

    }

}
