package com.example.dsandalgo.aaaa.basic;

public class PartitionAndQuickSort {

    /**
     * [2,5,6,1,9,4,7,3]
     *  0 1 2 3 4 5 6 7
     *  lessEqual
     *  index
     *
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition(int[] arr ,int l ,int r){
        if (l > r){
            return -1;
        }
        if (l == r){
            return l ;
        }
        int lessEqual = -1;
        int index = l;
        while (index < r){
            if (arr[index] <=  arr[r]){
                swap(arr, index , ++lessEqual);
            }
            index ++;
        }
        swap(arr, r, ++lessEqual );
        return lessEqual;
    }





    /**
     * [2,5,3,1,3,4,7,3]
     *  0 1 2 3 4 5 6 7
     *
     * 在数组arr[]上，玩荷兰国旗问题
     * 以arr[right] 值进行划分，小于arr[right]的在右边， 等于arr[right]的在中间，大于arr[right]的在右边
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, left};
        }
        int less = -1;
        int more = right;
        int index = left - 1;
        while (index < right) {
            if (arr[index] < arr[right]) {
                swap(arr, index++, ++less);
            }
            if (arr[index] == arr[right]) {
                index++;
            }
            if (arr[index] > arr[right]) {
                swap(arr, index, --more);
            }

        }
        swap(arr, right, more);
        return new int[]{less +1, more};

    }




    private static void swap(int[] arr, int index, int i) {
    }
}
