package com.example.dsandalgo.aaaa.basic;

import java.util.LinkedList;

/**
 * 加油站的良好出发点问题
 * https://leetcode.com/problems/gas-station
 */
public class GasStationCopy2023 {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] res = goodArray(gas, cost);
        for (int i = 0; i < res.length; i++) {
            if (res[i]) {
                return i;
            }
        }
        return -1;
    }

    private static boolean[] goodArray(int[] gas, int[] cost) {
        int n = gas.length;
        int m = n >> 1;
        int[] arr = new int[m];
        //纯能数组
        for (int i = 0; i < n; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + n] = gas[i] - cost[i];
        }
        //累加和数组
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i] + arr[i - 1];
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!queue.isEmpty() && arr[queue.peekLast()] > arr[i]) {
                queue.pollLast();
            }
            queue.add(i);

        }
        boolean[] good = new boolean[n];
        for (int i = 0, offset = 0, j = n; j < m; offset = arr[i++], j++) {
            if (queue.peekFirst() - offset >= 0) {
                good[i] = true;
            }
            if (queue.peekFirst() == i) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && arr[queue.peekLast()] > arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);

        }

        return good;


    }


}
