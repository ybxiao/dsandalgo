package com.example.dsandalgo.algo;


public class T_maxSumArraySum {

    public static void main(String[] args) {


    }

    public static int maxSum(int[] nums){
        //当前和
        int sum = 0;
        int ans  = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (sum >  0){
                sum +=nums[0];
            }else{
                sum = nums[0];
            }
            ans= Math.max(ans,sum);

        }


        return ans;
    }
}
