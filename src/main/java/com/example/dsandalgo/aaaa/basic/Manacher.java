package com.example.dsandalgo.aaaa.basic;

public class Manacher {


    /**
     * 给一个字符串，把他转换成可以manacher字符串，具体的做法就是在每一个字符串后面拼接任意的字符
     * 本例的实现中统一拼"#"
     *
     * @param str
     * @return
     */
    public static String manacherString(String str) {
        char[] charArray = str.toCharArray();
        char[] res = new char[charArray.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArray[index++];
        }
        return res.toString();

    }


    /**
     * 返回字符串s中最长回文子串的长度
     *
     * @param s
     * @return
     */
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String manacherString = manacherString(s);
        char[] strCharArray = manacherString.toCharArray();
        //回文半径数组
        int[] pArray = new int[strCharArray.length];
        //回文最右边界,此时代表第一个违规的位置
        int R = -1;
        //造成回文最右边界的中心点
        int C = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < strCharArray.length; i++) {

            pArray[i] = R > i ? Math.min(pArray[C * 2 - i], R - i) : 1;
            while (i + pArray[i] < strCharArray.length && i - pArray[i] > -1) {
                if (strCharArray[i + pArray[i]] == strCharArray[i - pArray[i]]) {
                    pArray[i]++;
                } else {
                    break;
                }
            }
            if (i + pArray[i] > R) {
                R = i + pArray[i];
                C = i;
            }
            max = Math.max(max, pArray[i]);

        }
        return max - 1;
    }


}
