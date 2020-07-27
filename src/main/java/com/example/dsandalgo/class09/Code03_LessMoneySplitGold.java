package com.example.dsandalgo.class09;

import java.util.PriorityQueue;

/**
 *
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 *
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 *
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 *
 */
public class Code03_LessMoneySplitGold {


        public static int lessMoney1(int[] arr){
            if (arr == null || arr.length <1){
                return 0;
            }

            int i = process(arr,0);
            return i;
        }

    private static int process(int[] arr, int pre) {

        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                   Math.min(ans, process(copyAndMergeTwo(arr,i,j),pre+arr[i]+arr[j]));
            }
        }
        return ans;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length -1];
        int m = 0;
        for (int k = 0; k < arr.length ; k++) {
            if (k != i || k !=j){
                ans[m++] = arr[k];
            }
        }
        ans[arr.length -1] = arr[i] +arr[j];
        return ans;
    }


    /**
     *
     * 第二种 贪心
     */
    public static int lowMoney2(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        int ans = 0;
        while (queue.size() > 1){
            int cur = queue.poll() + queue.poll();
            ans += cur;
            queue.add(ans);
        }
        return ans;


    }

}
