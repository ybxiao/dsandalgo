package com.example.dsandalgo.aaaa.basic;

import com.example.dsandalgo.aaaa.utils.CommonUtils;

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
public class MaxTopKCopy2023 {

    //使用直接排序的方法，直接去后k个数
    public static int[] maxTopK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        // 对 k进行修正，避免数组不够的情况
        k = Math.min(k, arr.length);
        Arrays.sort(arr);
        int[] ans = new int[k];
        int index = 0;
        for (int i = arr.length - 1; i >= arr.length - k; i--) {
            ans[index++] = arr[i];
        }
        return arr;

    }

    public static int[] maxTopK2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        //做一次 heapify O(N),此时最大的元素放在了开头
        for (int i = n - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        //最大的元素放到末尾
        CommonUtils.swap(arr, 0, --n);

        for (int i = 0; i < k - 1; i++) {
            heapify(arr, 0, --n);
            CommonUtils.swap(arr, 0, --n);
        }

        int index = 0;
        int[] ans = new int[k];
        for (int i = n - 1; i > n - k; i--) {
            ans[index++] = arr[i];
        }
        return ans;
    }


    public static int[] maxTopK3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }
        int n = arr.length;
        //想找topK，那就先找到第n-k小的数
        int num = minKth(arr, 0, n - 1, n - k);
        int[] ans = new int[k];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > num) {
                ans[count++] = arr[i];
            }
        }
        for (int i = count; i <= k; i++) {
            ans[count++] = num;
        }
        Arrays.sort(ans);
        for (int left = 0, right = k - 1; left <= right; left++, right--) {
            CommonUtils.swap(arr, left, right);
        }
        return ans;


    }

    private static int minKth(int[] arr, int left, int right, int index) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (left == right) {
            return arr[left];
        }
        int pivot = arr[left] + (int) (Math.random() * (arr[right] - arr[left]));
        int[] res = partition(arr, left, right, pivot);
        if (index < res[0]) {
            return minKth(arr, left, res[0], index);
        } else if (index > res[1]) {
            return minKth(arr, right, res[1], index);
        } else {
            return arr[res[0]];
        }
    }

    private static int[] partition(int[] arr, int left, int right, int pivot) {
        //初始化两个指针，标记小于区域 ，大于区域的初始范围
        int less = left - 1;
        int more = right + 1;
        while (left <= right) {
            if (left < pivot) {
                CommonUtils.swap(arr, left++, ++less);
            } else if (left > pivot) {
                CommonUtils.swap(arr, left, --more);
            } else {
                left++;
            }

        }
        return new int[]{less + 1, more - 1};
    }

    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int right = Math.min(left + 1, left);
            int larger = arr[left] > arr[right] ? left : right;
            if (larger == index) {
                break;
            }

            CommonUtils.swap(arr, index, larger);

            index = larger;
            left = larger * 2 + 1;
        }


    }

    public static void heapInsert(int[] arr, int index) {
        int cur = (index - 1) / 2;
        while (cur > 0) {
            if (arr[cur] < arr[index]) {
                CommonUtils.swap(arr, cur, index);
            }
            cur = (cur - 1) / 2;
        }

    }


}
