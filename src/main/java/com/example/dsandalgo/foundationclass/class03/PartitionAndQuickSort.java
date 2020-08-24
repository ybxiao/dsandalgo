package com.example.dsandalgo.foundationclass.class03;

/**
 * 分区
 * 快排
 */
public class PartitionAndQuickSort {


    public static int partition(int[] arr, int L, int R) {
        if (L >= R) {
            return -1;
        }
        int lessEqual = L - 1;
        int j = R - 1;
        while (L < j) {
            if (arr[L] <= arr[R]) {
                swap(arr, ++lessEqual, L);
            }
            L++;
        }
        swap(arr, lessEqual + 1, R);

        return lessEqual;

    }

    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;


    }

    public static int[] netherLandsFlag(int[] arr, int L, int R) {
        if (L >= R) {
            return new int[]{L, L};
        }

        int less = L - 1;
        int more = R + 1;
        int index = L;
        while (index < more) {

            if (arr[index] < arr[R]) swap(arr, ++less, index++);

            if (arr[index] == arr[R]) index++;

            if (arr[index] > arr[R]) swap(arr, index, --more);


        }
        swap(arr, more, R);
        return new int[]{less + 1, more};


    }

    public static void quickSort1(int[] arr){

        if (arr == null || arr.length <2){
            return;
        }

        process1(arr, 0 ,arr.length);

    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R){
            return;
        }
        int M = partition(arr,L,R);
        process1(arr,L,M-1);
        process1(arr,M+1,R);


    }



    public static void quickSort2(int[] arr){



    }
    public static void process2(int[] arr,int L ,int R){
        if (L >=R) {
            return;
        }
        int[] ints = netherLandsFlag(arr, L, R);
        process2(arr,L,ints[0]-1);
        process2(arr,ints[1],R);


    }

    private static void process3(int[] arr,int L,int R){
        if (L >=R) {
            return;
        }

        swap(arr,L+(int)Math.random()*(R-L+1),R);
        int[] ints = netherLandsFlag(arr, L, R);
        process3(arr,L,ints[0]-1);
        process3(arr,ints[1]+1,R);


    }




}
