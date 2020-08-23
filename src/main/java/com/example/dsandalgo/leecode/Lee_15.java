package com.example.dsandalgo.leecode;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 三元组，三数之和
 */
public class Lee_15 {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3){
            return null;
        }

        Collections.sort(CollectionUtils.arrayToList(nums));
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            int left = i+1;
            int right = nums.length -1;
            if (nums[i] > 0){
                break;
            }
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            while (left < right){

                int target  = nums[i] + nums[left] + nums[right];

                if (target == 0){
                    List temp =  new ArrayList();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    while (nums[left] == nums[left+1]) {left++;}
                    while (nums[right] == nums[right-1]) {right--;}
                }
                if (target > 0){
                    right --;
                }
                if (target < 0){
                    left ++;
                }

            }



        }


        return res;




    }
}
