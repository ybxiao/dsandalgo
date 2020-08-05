package com.example.dsandalgo.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * created on 2020/8/4.
 * time: 14:13
 *
 * @author yibo.xiao
 */


public class Lee_560 {

    public static int subArraySum(int[] nums,int k){

        int ans  = 0;

        Map<Integer,Integer> comMap  = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            comMap.put(i,nums[i]);

            for (int j = 0; j <=i; j++) {

                if (i !=j){
                    comMap.put(j,comMap.get(j)+nums[i]);
                }

                if (comMap.get(j) == k){
                    ans ++;

                }
            }
        }

        return ans;


    }

    public int subarraySum1(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }



    public static void main(String[] args) {
        System.out.println(subArraySum(new int[]{1,1,1}, 2));
    }

}
