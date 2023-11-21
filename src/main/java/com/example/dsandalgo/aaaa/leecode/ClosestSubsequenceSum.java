package com.example.dsandalgo.aaaa.leecode;

// 本题测试链接 : https://leetcode.com/problems/closest-subsequence-sum/
// 本题数据量描述:
// 1 <= nums.length <= 40
// -10^7 <= nums[i] <= 10^7
// -10^9 <= goal <= 10^9
// 通过这个数据量描述可知，需要用到分治，因为数组长度不大
// 而值很大，用动态规划的话，表会爆

import java.util.Arrays;

/**
 * 根据数据量猜解法
 */
public class ClosestSubsequenceSum {


    //分治法，左右两边部分最多能产生2^20个数
    public static int[] l = new int[1 << 20];
    public static int[] r = new int[1 << 20];


    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int n = nums.length;
        //需要使用左半部分的所有的数放入到l数组中，以及左半部分一共多少个数
        int lNum = process(nums, 0, n / 2, 0, 0, l);
        int rNum = process(nums, n / 2, n, 0, 0, r);
        Arrays.sort(l, 0, lNum - 1);
        Arrays.sort(r, 0, rNum - 1);
        int ans = goal;
        for (int i = 0; i < lNum; i++) {
            int rest = goal - l[i];
            while (rNum > 0 && (Math.abs(rest - r[rNum - 1]) < Math.abs(rest - r[rNum]))) {
                rNum--;
            }
            ans = Math.min(ans, Math.abs(rest - r[rNum]));
        }

        return ans;

    }

    /**
     * @param nums
     * @param index     从index...end上自由选择数字，把组成的累加和填入到l中
     * @param end
     * @param sum       0...index以及做好了选择，累加和放入到了sum中
     * @param fillIndex
     * @param l
     * @return fillIndex
     */
    private static int process(int[] nums, int index, int end, int sum, int fillIndex, int[] l) {
        if (index == end) {
            l[fillIndex++] = sum;
        } else {
            int curSum = sum + nums[index];

            fillIndex = process(nums, index + 1, end, curSum, fillIndex, l);

            //这里的入参fillIndex，是第一个选择之后新返回的
            fillIndex = process(nums, index + 1, end, sum, fillIndex, l);

        }
        return fillIndex;
    }


    public static void main(String[] args) {
        System.out.println(1 << 20);
    }


}
