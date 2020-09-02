package com.example.dsandalgo.camp01;

import java.util.LinkedList;

/**
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如果想达标，必须满足：
 * sub中最大值 – sub中最小值 <= num，
 * 返回arr中达标子数组的数量
 *
 *
 * 思路分析：
 * 子数组必须是连续的一段
 * 对问题进行拆解，分别计算以数组中每一个元素开头的子数组满足条件的个数
 *
 * 做两个滑动窗口，
 * 一个保存当前窗口最大值
 * 一个保存当前窗口的最小值
 *
 *
 */
public class Code02_AllLessNumSubArray {

    //滑动窗口是左闭右开的[1,5)
    public static int getNum(int[] arr,int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        LinkedList<Integer> maxQueue  = new LinkedList();
        LinkedList<Integer> minQueue  = new LinkedList();
        int left = 0;
        int right = 0;
        //结果值
        int ans = 0;

        while (left < arr.length){

            while (right < arr.length){
                //构造最大值窗口
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[right]){
                    maxQueue.pollLast();
                }
                maxQueue.addLast(right);

                //构造最小值窗口
                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[right]){
                    minQueue.pollLast();
                }
                minQueue.addLast(right);

                right ++;

                //此时right指针挺在了 第一个不满足条件的位置
                if (maxQueue.peekFirst() - minQueue.peekFirst() > num){
                   break;
                }



            }

            ans +=  right -left;

            if (maxQueue.peekFirst() == left){
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == left){
                minQueue.pollFirst();
            }
            left ++;
        }




        return ans;


    }


    /*//滑动窗口是左闭右开的[1,5)
    public static int getNum1(int[] arr,int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        LinkedList<Integer> maxQueue  = new LinkedList();
        LinkedList<Integer> minQueue  = new LinkedList();
        int left = 0;
        int right = 0;
        //结果值
        int ans = 0;



    while (right < arr.length){
        //构造最大值窗口
        while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[right]){
            maxQueue.pollLast();
        }
        maxQueue.addLast(right);

        //构造最小值窗口
        while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[right]){
            minQueue.pollLast();
        }
        minQueue.addLast(right);

        right ++;

        //此时right指针挺在了 第一个不满足条件的位置
        if (maxQueue.peekFirst() - minQueue.peekFirst() > num){
            ans +=  right -left;

            if (maxQueue.peekFirst() == left){
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == left){
                minQueue.pollFirst();
            }
            left ++;
        }

        }




        return ans;


    }*/



    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));

    }

}
