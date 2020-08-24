package com.example.dsandalgo.foundationclass.class03;

/**
 *在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 *
 */
public class SmallSum {

    public static int smallSum(int[] arr){
        if (arr == null || arr.length < 2){
            return -1;
        }
        return process(arr,0,arr.length-1);


    }

    /**
     * 定义数组arr从L到R范围上，排序并返回小和
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int process(int[] arr, int L ,int R){
        if (L >= R){
            return 0;
        }
        int M = L + (R-L) >>1;
        return process(arr,L,M)
                +process(arr,M+1,R)
                +merge(arr,L,M,R);

    }


    public static int merge(int[]arr,int L,int M,int R){
        int[] help =new int[R-L+1];
        //记录help数组的位置
        int k = 0;
        int i = L;
        int j = M+1;
        int res = 0;
        while (i <= M && j <= R){
            if (arr[i] < arr[j]){
                help[k++] = arr[i++];
                res += (R-j+1) * arr[i];
            }
            if (arr[i] >= arr[j]){
                help[k++] = arr[j++];
            }
        }
        while (i<=M){
            help[k++] = arr[i++];
        }
        while (j <= R){
            help[k++] = arr[j++];
        }
        for (int l = 0; l <help.length; l++) {
            arr[L+l] = help[l];
        }

        return res;


    }
}
