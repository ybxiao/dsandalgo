package com.example.dsandalgo.aaaa.basic;

public class LongestSubstringWithoutRepeatingCharactersCopy2023 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        map[arr[0]] = 0;
        int res = 0;
        int pre = 1;
        for (int i = 1; i < arr.length; i++) {
            pre = Math.min(i - map[arr[i]], pre + 1);
            res = Math.max(pre, res);
            map[arr[i]] = i;

        }
        return res;


    }
}
