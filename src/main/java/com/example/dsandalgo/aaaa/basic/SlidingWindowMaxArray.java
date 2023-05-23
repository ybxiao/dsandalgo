package com.example.dsandalgo.aaaa.basic;

import java.util.LinkedList;

/**
 * 窗口内最大值或最小值更新结构的实现
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class SlidingWindowMaxArray {

    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int n = arr.length;
        int[] res = new int[n - w + 1];
        int index = 0;
        int left = 0;
        int right = w - 1;
        while (right < n) {
            int ans = arr[left];
            for (int i = left + 1; i <= right; i++) {
                ans = Math.max(arr[i], ans);
            }
            res[index++] = ans;
            left++;
            right++;
        }

        return res;

    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qMax = new LinkedList();
        int[] res = new int[arr.length - w + 1];
        int i = 0;

        for (int r = 0; r < arr.length; r++) {
            while (!qMax.isEmpty() && qMax.peekLast() <= arr[r]) {
                qMax.pollLast();
            }
            qMax.addLast(r);
            //先维持窗口
            if (qMax.peekFirst() == r - w) {
                qMax.pollFirst();
            }
            //再收集答案
            if (r >= w - 1) {
                res[i++] = arr[qMax.peekFirst()];
            }

        }
        return res;

    }


}
