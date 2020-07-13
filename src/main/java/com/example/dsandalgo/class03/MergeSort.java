package com.example.dsandalgo.class03;


/**
 * 归并排序
 * 递归实现
 * 迭代实现
 */
public class MergeSort {

    public void merge(int[] arr,int L ,int M ,int R){

        int[] help = new int[R-L+1];
        int k =0;
        int i = L;
        int j = M+1;
        while (i<=M  && j<=R){
            if (arr[i] <= arr[j]){
                help[k++] = arr[i];
                i++;
            }else{
                help[k++] = arr[j];
                j++;
            }
        }

        while (j <= R){
            help[k++] = arr[j++];
        }


        while (i<=M){
            help[k++] = arr[i++];
        }


        for (int l = 0; l <=help.length ; l++) {
            arr[L+l] = help[l];
        }
    }

    //递归处理
    public void mergesort1(int[] arr){
        if (arr == null || arr.length <2){
            return;
        }
        //递归函数的定义，让数组arr 0 -- len-1 的位置上变有序
        process(arr , 0 ,arr.length-1);


    }

    public void process(int[]arr ,int L ,int R){
        if (L >= R){
            return;
        }
        int M = L + (R-L) >> 2;
        process(arr,L ,M);
        process(arr,M+1,R);
        merge(arr,L,M,R);

    }

    //迭代
    public void mergeSort2(int[] arr){
        if (arr == null || arr.length <2){
            return;
        }
        int N = arr.length;
        int mergesize =1;
        int L = 0;
        while (mergesize < N){
            while ( L < N){

                int M =  L +mergesize -1;
                int R = Math.min(M+mergesize,N-1);
                if ( R >= N){
                    break;
                }
                merge(arr,L ,M,R);
                L = R +1;
            }
            if (mergesize > N >>2){
                break;
            }
            mergesize *=2;
        }
    }
}
