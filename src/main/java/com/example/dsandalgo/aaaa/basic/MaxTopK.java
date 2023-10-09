package com.example.dsandalgo.aaaa.basic;

import java.util.Arrays;

/**
 * 设计在无序数组中收集最大的前K个数字的算法（根据不同的三个时间复杂度，设计三个算法）
 * 给定一个无序数组arr中，长度为N，给定一个正数k，返回top k个最大的数
 * 不同时间复杂度三个方法：
 * 1）O(N*logN) :直接先排序，然后找出后k个。
 * 2）O(N + K*logN)：预期：把tok K的数放在数组末尾，然后收集；
 * 先用heapify建一个大根堆O（N），再进行k次堆调整，把top k依次沉底，然后收集；
 * 3）O(n + k*logk)
 * 要求top k的数，先找出第（n-k）小的数
 */
public class MaxTopK {


    public static int[] maxTopK2(int[] arr, int k) {
        //参数校验
        if (arr == null || arr.length == 0) {
            return null;
        }
        // k值修正，考虑arr的长度不足k的情况
        k = Math.min(k, arr.length);
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        int count = 1;
        for (; count < k; count++) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
            count++;
        }
        int[] res = new int[k];
        int index = 0;
        for (int i = arr.length - 1; i >= 0 && index < k; i++) {
            res[index++] = arr[i];
        }
        return res;

    }

    //使用heapify进行建堆
    private static void heapify(int[] arr, int index, int n) {
        int leftNode = index * 2 + 1;
        while (leftNode < n) {
            //左右孩子中更大的那个
            int largest = ((leftNode + 1) < arr.length) && arr[leftNode + 1] > arr[leftNode] ? leftNode + 1 : leftNode;
            //找出左右孩子和index节点三者中的最大者
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            leftNode = index * 2 + 1;

        }
    }

    /**
     * 插入建堆
     *
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr, int index) {

        while (index > 0) {
            int father = index / 2;
            if (arr[index] > arr[father]) {
                swap(arr, index, father);
            }


            index = father;

        }

    }

    /**
     * @param arr 无序数组，长度为n
     * @param k   求无序数组中top k的元素
     * @return 1） 先找出第n-k小的数x（O(N)）
     * 2)  遍历数组，凡是大于x的数都收集起来，此时肯定不足k个
     * 3） 从大到小排序
     * 4） 取前k个
     */
    public static int[] maxTopK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[]{};
        }
        int n = arr.length;
        //兼容数组长度n 比 k小的情况；
        k = Math.min(n, k);
        int num = minKth(arr, n - k);
        int[] ans = new int[k];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > num) {
                ans[index++] = arr[i];
            }
        }
        for (; index < k; index++) {
            ans[index] = num;
        }
        Arrays.sort(ans);
        for (int left = 0, right = ans.length - 1; left < right; left++, right--) {
            swap(arr, left, right);
        }
        return arr;

    }

    /**
     * 找出数组中第index小的数
     *
     * @param arr
     * @param index
     * @return
     */
    private static int minKth(int[] arr, int index) {
        return 0;
    }


    private static void swap(int[] arr, int index, int largest) {
        int temp = arr[index];
        arr[index] = arr[largest];
        arr[largest] = temp;

    }


}
