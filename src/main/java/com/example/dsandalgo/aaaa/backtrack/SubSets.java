package com.example.dsandalgo.aaaa.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SubSets {

    /**
     * 题目给你输入一个无重复元素的数组nums，其中每个元素最多使用一次，请你返回nums的所有子集。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subSets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();

        backTrack(nums, res, track, 0);
        return res;
    }

    private void backTrack(int[] nums, List<List<Integer>> res, LinkedList<Integer> track, int index) {
        res.add(track);
        for (int i = index; i < nums.length; i++) {
            track.addLast(nums[i]);
            backTrack(nums, res, track, index + 1);
            track.removeLast();
        }
    }

}
