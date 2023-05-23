package com.example.dsandalgo.aaaa.basic;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 加油站的良好出发点问题
 * https://leetcode.com/problems/gas-station
 */
public class GasStation {

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] booleans = goodArray(gas, cost);
        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                return i;
            }
        }

        return -1;
    }

    private static boolean[] goodArray(int[] gas, int[] cost) {
        int length = gas.length;
        int m = length << 1;
        //拼接好的数组，纯能数组（燃气-距离）
        int[] arr = new int[m];
        for (int i = 0; i < length; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + length] = gas[i] - cost[i];
        }

        //前缀和数组
        for (int i = 1; i < m; i++) {
            arr[i] += arr[i - 1];
        }

        //对arr数组进行操作,初始化一个长度为length的窗口
        LinkedList<Integer> minWindow = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[i]) {
                minWindow.pollLast();
            }
            minWindow.addLast(i);
        }
        boolean[] res = new boolean[length];

        for (int offset = 0, index = 0, i = length; i < m; offset = arr[index++], i++) {
            if (arr[minWindow.peekFirst()] - offset >= 0) {
                res[i] = true;
            }
            if (minWindow.peekFirst() == i) {
                minWindow.pollFirst();
            }
            while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[i]) {
                minWindow.pollLast();
            }
            minWindow.addLast(i);

        }
        return res;


    }


}
