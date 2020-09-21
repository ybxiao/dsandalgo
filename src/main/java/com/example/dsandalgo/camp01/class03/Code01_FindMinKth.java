package com.example.dsandalgo.camp01.class03;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在无序数组中求第K小的数
 * 0）使用堆的数据结构
 * 1）改写快排的方法
 * 2）bfprt算法
 *
 * 大根堆
 * 快排思路
 * bfprt算法
 *
 */
public class Code01_FindMinKth {

    public static class MyHeapComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }


    //使用小根堆
    public static int minKth1(int[] arr,int k){

        PriorityQueue<Integer> queue = new PriorityQueue<>(new MyHeapComparator());

        for (int i = 0; i < k; i++) {
            queue.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()){
                queue.poll();
                queue.add(arr[i]);
            }
        }
        return queue.peek();


    }

    public static int minKth2(int[]arr, int k){
        //copy到新数组进行操作，不改变原有数组的结构和数值
        int[] newArray = copyarray(arr);

        return process(newArray,0,arr.length-1,k-1);

    }

    private static int process(int[] newArray, int left , int right, int index) {
        if (left == right){
            return newArray[left];
        }

        int pivot =  newArray[left + (int)Math.random() * (right - left +1)];

        int[] range = partition(newArray,left,right,pivot);
        if (index >= range[0] && index <= range[1]){
            return newArray[index];
        }else if (index < range[0]){
            return process(newArray,left,range[0]-1,index);
        }else{
            return process(newArray,range[1]+1,right,index);
        }


    }

    //荷兰国旗划分
    private static int[] partition(int[] newArray, int left, int right, int pivot) {
        int less = left -1;
        int more = right+1;
        int cur  =  left;
        while (cur < more){
            if (newArray[cur] < pivot){
                swap(newArray,++less,cur++);
            }else if (newArray[cur] > pivot){
                swap(newArray,--more,cur);
            }else{
                cur ++;
            }

        }
        return new int[]{less+1,more-1};


    }

    private static void swap(int[] newArray, int i, int j) {
        int temp = newArray[i];
        newArray[i] =  newArray[j];
        newArray[j] =  temp;
    }

    private static int[] copyarray(int[] arr) {
        int n  = arr.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] =  arr[i];
        }
        return temp;
    }

    public static int minKth3(int[] array, int k){
        int[] arr =  copyarray(array);

        return bfprt(arr,0,arr.length-1,k-1);
    }

    private static int bfprt(int[] arr, int left, int right, int index) {
        if (left == right){
            return arr[left];
        }
        int pivot = medianOfMedians(arr,left,right);
        int[] range = partition(arr, left, right, pivot);
        if (index >= range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return bfprt(arr,left,range[0]-1,index);
        }else{
            return bfprt(arr,range[1]+1,right,index);
        }


    }

    //获取合适的中位数
    //bfprt和快排的区别点就在于此，根据求得的划分值使得parition的效率为O(N)
    private static int medianOfMedians(int[] arr, int left, int right) {
        int size = right -left +1;
        int len = size / 5;
        int offset =  size % 5 == 0 ? 0:1;
        int[] mArray =  new int[len + offset];
        for (int team = 0; team < mArray.length; team++) {

            int teamFirst = left + team * 5 ;
            mArray[team] =  getMedian(arr,teamFirst,Math.min(right,teamFirst + 4));
        }

        return bfprt(mArray,0, mArray.length-1,mArray.length/2);
    }

    private static int getMedian(int[] arr, int left, int right) {
        insertionSort(arr,left,right);
        return arr[(right+left)/2];

    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left+1 ; i <right ; i++) {
            for (int j = i-1; j >=left ; j--) {
                if (arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
