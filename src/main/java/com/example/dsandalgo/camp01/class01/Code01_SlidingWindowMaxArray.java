package com.example.dsandalgo.camp01.class01;

import java.util.LinkedList;

/**
 * 滑动窗口
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 *
 *
 * 分析：
 * 滑动窗口设计为双端队列
 * 从大到小的顺序
 *
 */
public class Code01_SlidingWindowMaxArray {

    // 自己练习
    public static int[] gatMaxWindowForX(int[] arr, int w){
        if (arr == null || w < 1 ||  arr.length < w){
            return new int[]{-1};
        }
        LinkedList<Integer> max = new LinkedList<>();
        int[] res = new int[arr.length-w+1];

        int L  = -1;
        int R  = 0;
        while (R <  arr.length){

            while (!max.isEmpty() && arr[max.peekLast()] <= arr[R]){
                max.pollLast();
            }
            max.addLast(R);

           /* if (R-L >= w){
                res[R] = max.peekFirst();
                if (L == max.peekFirst()){
                    max.pollFirst();
                }
                L++;
            }*/

            R++;

        }

        return res;

    }

    public static int[] getMaxWindow(int[] arr,int w){
        if (arr == null  || w < 1 || arr.length < w){
            return null;
        }
        //滑动窗口里面存放的最大值
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res =  new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {

            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){
                qMax.pollLast();
            }
            //注意放的是下标
            qMax.addLast(i);
            if (qMax.peekFirst() == i - w){
                qMax.pollFirst();
            }

            if (i >= w - 1){
                res[index++] = arr[qMax.peekFirst()];
            }

        }

        return res;

    }

    public static int[] rightWay(int[] arr,int w){
        if (arr == null  || w < 1 || arr.length < w){
            return null;
        }
        int L  = 0;
        int index = 0;
        int R = w-1;
        int[] res = new int[arr.length-w+1];
        while(R < arr.length){
            int max = arr[L];
            for (int i = L; i <= R; i++) {
                max = Math.max(arr[i],max);
            }
            res[index++] = max;
            L++;
            R++;

        }
        return res;

    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);

            int[] ans2 = rightWay(arr, w);

            int[] ans3 = gatMaxWindowForX(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
