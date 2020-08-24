package com.example.dsandalgo.foundationclass.class05;

public class Code03_CountSort {

    public static void countSort(int[] arr){
        if (arr ==null || arr.length <2){
            return;
        }
        int Max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Max = Math.max(Max,arr[i]);
        }
        int[] ints = new int[Max];
        for (int i = 0; i < arr.length;  i++) {
            ints[arr[i]]++;
        }
        int k = 0;
        for (int i = 0; i < ints.length; i++) {
            while (ints[i]-- > 0){
                arr[k++] = i;
            }
        }
    }

}
