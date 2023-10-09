package com.example.dsandalgo.aaaa.basic;

import java.util.LinkedList;

/**
 * 窗口内最大值或最小值更新结构的实现
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class SlidingWindowMaxArrayCopy2023 {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length == 0 || arr.length < w) {
            return null;
        }
        int n = arr.length;
        //维持从大到小
        LinkedList<Integer> window = new LinkedList<>();
        int[] res = new int[n - w + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {

            while (!window.isEmpty() && arr[window.peekLast()] <= arr[i]) {
                window.pollLast();
            }
            window.addLast(i);
            if (window.peekFirst() == i - w) {
                window.pollFirst();
            }
            if (i > w) {
                res[index++] = arr[window.peekFirst()];
            }


        }

        return res;


    }


}
