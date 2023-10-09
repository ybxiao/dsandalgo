package com.example.dsandalgo.aaaa.basic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMinKth {
    //定义一个比较器
    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    //利用大根堆，时间复杂度 O（N +  N* logK）
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MaxHeapComparator());
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

    /**
     * 使用快排
     * 1、copy数组，保证原数据的纯洁，因为快排会改变原数组的元素属性
     * 2、利用快排的partition，找出arr[x]元素，在排序后数组中的索引范围[i,j]
     * 3、如果k-1恰好[i,j]中，则直接返回arr[x]，否则重复在[0,i) 或者（j,n]重复步骤二和步骤三。
     *
     * @param arr
     * @param k
     * @return
     */
    public static int minKth2(int[] arr, int k) {
        int[] source = copyArray(arr);
        return process2(source, 0, arr.length - 1, k - 1);
    }

    private static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    /**
     * 在数组arr的[left,right]范围上，找index位置的数
     * 潜在含义：index在[left,right]范围上
     *
     * @param arr
     * @param left
     * @param right
     * @param index
     * @return
     */
    public static int process2(int[] arr, int left, int right, int index) {
        if (left == right) {
            return arr[left];
        }
        int pivot = arr[left + (int) (Math.random() * (right - left))];
        int[] res = partition(arr, left, right, pivot);
        if (index >= res[0] && index <= res[1]) {
            return arr[index];
        } else if (index < res[0]) {
            return process2(arr, left, res[0] - 1, index);
        } else {
            return process2(arr, res[1] + 1, right, index);
        }


    }

    /**
     * 在数组arr上，做partition，小于pivot放在左边，等于pivot的放在中间，大于pivot的放在右边
     *
     * @param arr
     * @param left
     * @param right
     * @param pivot
     * @return 等于区域的左边界和右边界
     */
    private static int[] partition(int[] arr, int left, int right, int pivot) {
        int less = left - 1;
        int more = right + 1;
        int cur = left;

        while (cur <= right) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else{
                cur ++;
            }
        }
        return new int[]{less +1, more -1};


    }

    /**
     * 交换arr中i和j位置的值
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
