package com.example.dsandalgo.aaaa.basic;

public class LongestSubstringWithoutRepeatingCharactersCopy2023V2 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] pos = new int[256];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = -1;
        }
        pos[chars[0]] = 0;
        int ans = 1;
        int pre = 1;
        for (int i = 1; i < chars.length; i++) {
            int cur = Math.max(i - pos[chars[i]], pre + 1);
            ans = Math.max(cur, ans);
            pos[chars[i]] = i;
            pre = cur;
        }
        return ans;

    }
}
