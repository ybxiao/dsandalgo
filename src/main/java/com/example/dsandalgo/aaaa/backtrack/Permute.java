package com.example.dsandalgo.aaaa.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组nums，返回其所有可能的全排列。
 * <p>
 * 函数签名如下：
 * <p>
 * List<List<Integer>> permute(int[] nums)
 * 比如输入nums = [1,2,3]，函数的返回值应该是：
 * <p>
 * [
 * [1,2,3],[1,3,2],
 * [2,1,3],[2,3,1],
 * [3,1,2],[3,2,1]
 * ]
 */
public class Permute {

    /**
     * @param nums 无重复元素的数组
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];

        backTrack1(nums, used, res, track);

        return res;
    }

    /**
     * @param nums
     * @param used
     * @param res
     * @param track
     */
    private static void backTrack1(int[] nums, boolean[] used, List<List<Integer>> res, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(track);
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.addLast(nums[i]);
            backTrack1(nums, used, res, track);
            used[i] = false;
            track.removeLast();
        }
    }


    /**
     * nums数组中的元素无重复且可复选的情况下，会有哪些排列？
     *
     * 比如输入nums = [1,2,3]，那么这种条件下的全排列共有 3^3 = 27 种：
     *
     * [
     *   [1,1,1],[1,1,2],[1,1,3],[1,2,1],[1,2,2],[1,2,3],[1,3,1],[1,3,2],[1,3,3],
     *   [2,1,1],[2,1,2],[2,1,3],[2,2,1],[2,2,2],[2,2,3],[2,3,1],[2,3,2],[2,3,3],
     *   [3,1,1],[3,1,2],[3,1,3],[3,2,1],[3,2,2],[3,2,3],[3,3,1],[3,3,2],[3,3,3]
     * ]
     */


}
