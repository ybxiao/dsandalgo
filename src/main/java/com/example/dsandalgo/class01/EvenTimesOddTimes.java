package com.example.dsandalgo.class01;

import java.util.HashMap;
import java.util.Map;

public class EvenTimesOddTimes {

    public void evenTimesOddTimes(int[] nums){
        int eor = 0;
        for (int i = 0; i < nums.length; i++) {
            eor = eor ^ nums[i];
        }
        System.out.println(eor);

        Map map  =new HashMap();
    }


}
