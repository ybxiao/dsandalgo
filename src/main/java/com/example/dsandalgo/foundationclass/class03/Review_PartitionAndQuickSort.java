package com.example.dsandalgo.foundationclass.class03;

/**
 * created on 2020/8/27.
 * time: 14:57
 * 分区、荷兰国旗算法、快排复习
 * @author yibo.xiao
 */


public class Review_PartitionAndQuickSort {

    /*

    以arr[right]为给定值，对数组arr 从left到right的范围上进行分区
    小于arr[right]的在左侧，大于arr[right]的在右侧，=arr[right]的在中间
    返回第一个==arr[right]的位置上变有序
    定义小于区域边界 less =  left -1
    定于大于区域边界 more = right



    */


    /**
     * 遍历所有元素，当前元素为i
     * arr[i] 小于或者等于目标元素时： 小于区域往前移动一个，交换当前位置元素和小于区域最后一个元素
     * i++
     * arr[i] 大于目标元素时： i++ 往后移动一个
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] arr, int left ,int right){
        if (arr == null  || left > right){
            return -1;
        }
        int lessEqual = left - 1;
        int moreEqual = right;
        int index = left;

        while (index < moreEqual){
            if (arr[index] <= arr[right]){
                swap(arr,++lessEqual,index);
            }
            index ++;
        }
        swap(arr,lessEqual+1,right);

        return lessEqual;
    }

    /**
     * 荷兰国旗partition算法
     * 以arr[R] 进行partition
     * 定义左区域边界 less = L-1
     * 定义右区域边界 more = R
     * 遍历对待排序的元素
     *
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherLandsFlag(int[] arr,int L ,int R){
        if (L >= R){
            return  null;
        }

        int less  = L-1;
        int more = R;

        while (L < more){
            if (arr[L] <  arr[R]){
                swap(arr,++less,L++);
            }
            if (arr[L] == arr[R]) {
                L ++;
            }
            if (arr[L] > arr[R]){
                swap(arr,L,--more);
            }
        }
        swap(arr,L,R);

        return new int[]{less+1,more};



    }

    public static  int[] netherLandsFlagV2(int[] arr , int left , int right){
        if (left > right){
            return null;
        }
        int less = left -1;
        int more = right;
        int index  =  left;
        while (index < more){
            if (arr[index] < arr[more]){
                swap(arr, ++less, index ++);
            }

            if (arr[index] == arr[more]){
                index ++;
            }

            if (arr[index] > arr[more]) {
                swap(arr, index, --more);
            }

            swap(arr, index, right);
        }

        return  new int[]{less+1, more};

    }


    //递归快排
    public static void quickSort1(int[] arr,int L ,int R){
        if (arr == null || L > R){
            return;
        }

        int partition = partition(arr, L, R);
        quickSort1(arr,L,partition-1);
        quickSort1(arr,partition+1,R);


    }
















    public static void swap(int[] arr, int i ,int j)   {

    }

}
