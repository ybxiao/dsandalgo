package com.example.dsandalgo.aaaa.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 给定两个整数n和k，返回范围[1, n]中所有可能的k个数的组合。
 * 比如combine(3, 2)的返回值应该是：
 * <p>
 * [ [1,2],[1,3],[2,3] ]
 */
public class Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList();
        backTrack1(n, res, track, k, 0);
        return res;
    }

    private void backTrack1(int n, List<List<Integer>> res, LinkedList<Integer> track, int k, int start) {
        if (track.size() == k) {
            res.add(track);
            return;
        }
        for (int i = start; i < n; i++) {
            track.addLast(i);
            backTrack1(n, res, track, k, i + 1);
            track.removeLast();

        }
    }

    /**
     * 给你一个无重复元素的整数数组candidates和一个目标和target，
     * 找出candidates中可以使数字和为目标数target的所有组合。
     * candidates中的每个数字可以无限制重复被选取。
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack2(candidates, res, track, 0, 0, target);
        return res;

    }

    private void backTrack2(int[] candidates, List<List<Integer>> res, LinkedList<Integer> track, int start, int trackSum, int target) {
        if (trackSum == target) {
            res.add(track);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (trackSum + candidates[i] > target){
                continue;
            }
            trackSum += candidates[i];
            track.addLast(candidates[i]);
            backTrack2(candidates, res, track, i, trackSum, target);
            trackSum -= candidates[i];
            track.removeLast();
        }


    }


}
