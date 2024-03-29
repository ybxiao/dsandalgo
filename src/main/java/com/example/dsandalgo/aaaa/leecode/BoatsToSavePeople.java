package com.example.dsandalgo.aaaa.leecode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/boats-to-save-people/
 * <p>
 * 给定一个正数数组arr，代表若干人的体重，
 * 再给定一个正数limit，表示所有船共同拥有的载重量，
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，并且用最好的分配方法让船尽量少，返回最少的船数
 */
public class BoatsToSavePeople {


    public static int numRescueBoats(int[] people, int limit) {
        if (people == null || people.length == 0) {
            return 0;
        }
        Arrays.sort(people);
        int ans = 0;
        int l = 0;
        int r = people.length - 1;
        int sum = 0;
        while (l <= r) {
            sum = l == r ? people[l] : people[l] + people[r];
            if (sum <= limit) {
                ans++;
                l++;
                r--;
            } else {
                r--;
                ans++;
            }
        }
        return ans;


    }

}
