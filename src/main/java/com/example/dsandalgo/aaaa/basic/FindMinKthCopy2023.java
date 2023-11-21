package com.example.dsandalgo.aaaa.basic;

import com.example.dsandalgo.aaaa.utils.CommonUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

//找到数组中第K小的数，两种方式

/**
 * 1、快排的方式
 * 2、小根堆
 */
public class FindMinKthCopy2023 {
    //定义一个比较器，定义大根堆
    public static Comparator<Integer> maxComparator = (o1, o2) -> o2 - o1;

    public static int minKth1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || arr.length < k) {
            return 0;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(maxComparator);
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < heap.peek()) {
                heap.poll();
                heap.add(arr[i]);
            }

        }
        return heap.peek();
    }

    //使用快排
    public static int minKth2(int[] arr, int k) {
        int[] copy = copyArrays(arr);
        return process(arr, 0, arr.length, k - 1);


    }

    /**
     * @param arr
     * @param left
     * @param right
     * @param index 在数组left...right范围上，找到index（索引）位置的数
     * @return
     */
    private static int process(int[] arr, int left, int right, int index) {
        if (left == right) {
            return arr[left];
        }
        int pivot = arr[left] + (int) (Math.random() * (arr[left] - arr[right]));
        int[] res = partition(arr, left, right, pivot);
        if (res[0] <= index && index <= res[1]) {
            return arr[res[0]];
        } else if (index < res[0]) {
            return process(arr, left, res[0] - 1, index);
        } else {
            return process(arr, res[1] + 1, right, index);
        }


    }

    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left - 1;
        int more = right + 1;
        for (int i = left; i <= right; i++) {
            if (arr[i] < pivot) {
                CommonUtils.swap(arr, i++, ++less);
            } else if (arr[i] == pivot) {
                i++;
            } else {
                CommonUtils.swap(arr, i, --more);
            }
        }


        return new int[]{less + 1, more - 1};
    }

    private static int[] copyArrays(int[] arr) {
        return new int[0];
    }


}
