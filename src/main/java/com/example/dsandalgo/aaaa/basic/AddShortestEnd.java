package com.example.dsandalgo.aaaa.basic;

import java.time.chrono.MinguoDate;

/**
 * 给定一个字符串str，只能在str的后面添加字符，想让str整体变成回文串，返回至少要添加几个字符
 * 此题利用manacher算法进行求解
 * pArray[i] 回文半径数组
 * R回文左右边界
 * C当取得回文最右边界时的回文中心
 * 解题思路：当回文最右边界覆盖到字符串最后一个字符时，此时的回文中心点是最优中心点，可以当作整体回文中心点，
 * 以此未回文中心，在字符串结尾添加数字
 */
public class AddShortestEnd {
    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] strArray = manacherString(s);
        int[] pArray = new int[strArray.length];
        int center = -1;
        int mostR = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i < strArray.length; i++) {
            pArray[i] = mostR > i ? Math.min(pArray[center * 2 - i], mostR - i) : 1;
            while (pArray[i] < strArray.length && (i - pArray[i]) >= 0) {
                if (strArray[i + pArray[i]] == strArray[i - pArray[i]]) {
                    pArray[i]++;
                }else{
                    break;
                }
            }
            if (i + pArray[i] > mostR) {
                mostR = i + pArray[i];
                center = i;
            }
            if (mostR == strArray.length) {
                maxContainsEnd = pArray[i];
                break;
            }

        }
        char[] res = new char[strArray.length - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = strArray[i * 2 + 1];
        }
        return String.valueOf(res);

    }

    private static char[] manacherString(String s) {
        char[] charArray = s.toCharArray();
        char[] res = new char[charArray.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArray[index++];
        }

        return res;
    }
}
