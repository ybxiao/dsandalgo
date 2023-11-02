package com.example.dsandalgo.aaaa.basic;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        int[] map = new int[256];
        int n = charArray.length;
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        map[charArray[0]] = 0;
        int res = 0;
        int pre = 1;
        for (int i = 1; i < n; i++) {
            pre = Math.min(i - map[charArray[i]], pre + 1);
            res = Math.max(pre, res);
            map[charArray[i]] = i;
        }


        return res;


    }
}
