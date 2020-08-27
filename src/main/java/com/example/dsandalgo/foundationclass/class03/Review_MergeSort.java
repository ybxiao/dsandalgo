package com.example.dsandalgo.foundationclass.class03;

/**
 * created on 2020/8/27.
 * time: 16:02
 * 归并排序 复习版本
 * @author yibo.xiao
 */


public class Review_MergeSort {
    //数组arr分别在L...M 和 M+1...R有序 合并使其整体有序
    // arr L...M范围 和 M+1 ... R 范围
    public static void merge(int[]arr ,int L ,int M ,int R){
        if (L >  M  ||  M >R){
            return;
        }
        int[] help = new int[R-L+1];
        int j  = M+1;
        int i = 0;
        while (L <= M && j <= R){
            if (arr[L] <= arr[j]){
                help[i++] = arr[L++];
            }else{
                help[i++] = arr[j++];
            }
        }
        while (i< R-L){
            help[i++] =  L>M ? arr[j++]: arr[L++];
        }

        for (int k = 0; k < help.length; k++) {
            arr[L+k] =  help[k];
        }
    }


    public static void mergeSort1(int[] arr){
        if (arr == null  || arr.length < 1){
            return;
        }
        process(arr,0,arr.length-1);

    }

    public static void process(int[] arr,int L ,int R){
        if(L  == R){
            return;
        }
        int M =  L + (R-L) >> 1 ;
        process(arr,L,M);
        process(arr,M+1,R);
        merge(arr,L,M,R);

    }

    //迭代
    public static void mergeSort2(int[] arr){
        if (arr == null || arr.length <1){
            return;
        }
        int mergeSize = 1;
        int L = 0;
        int N = arr.length;

        while (mergeSize <  N){
            while (L < N){
                int M = L + mergeSize -1;
                if (M >=N){
                    break;
                }

                int R = Math.min(M+mergeSize,N-1);
                merge(arr,L,M,R);
                L = R+1;



            }
           if (mergeSize > N >>1){
               break;
           }
           mergeSize <<=1;
        }



    }
}
