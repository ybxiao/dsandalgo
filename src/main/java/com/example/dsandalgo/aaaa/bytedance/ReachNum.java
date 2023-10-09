package com.example.dsandalgo.aaaa.bytedance;

/**
 * 一开始在0位置，每一次都可以向左或者向右跳
 * 第i次能向左或者向右跳严格的i步
 * 请问从0到x位置，至少跳几次可以到达
 */
public class ReachNum {

    //dp[index][i]
    public static int reachNum(int target){
        if (target == 0){
            return 0;
        }
        int step = 0;
        int curTarget = 0;
        while (curTarget < target && (((curTarget - target) & 1) == 1)){
            curTarget += ++ step;
        }
        return step;

    }

}
