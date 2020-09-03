package com.example.dsandalgo.foundationclass.class04;

public class Review_Code04_HeapSort {

    public static void heapSort(int[] arr){

        if (arr ==null || arr.length <2){
            return;
        }
        //N * LogN
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }

        //O(N)
        for (int i = arr.length-1 ; i >= 0 ; i --) {
            heapify(arr,i,arr.length);
        }

        int heapSize = arr.length ;
        swap(arr,0,--heapSize);

        while (heapSize > 0){

            //O(logN)
            heapify(arr,0 ,heapSize);
            swap(arr,0,--heapSize);
        }




    }



    public static void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }


    }
    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }

    public static void heapify(int[]arr ,int index,int heapSize){
        int left = index * 2 +1;
        while (left < heapSize){
            //右孩子存在，且右孩子节点的值大于左孩子节点的值。
            int largest = (left +1 < heapSize)  && (arr[left +1] > arr[left]) ? left+1:left;

            largest = arr[largest] > arr[index] ? largest:index;

            if (largest == index){
                break;
            }
            swap(arr,index,largest);
            index = largest;
            left = index *2 +1;

        }



    }



}
