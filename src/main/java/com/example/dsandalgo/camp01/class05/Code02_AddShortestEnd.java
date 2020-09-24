package com.example.dsandalgo.camp01.class05;


/**
 * manacher算法的运用
 *
 * 题目：
 * 给定一个字符串
 * 只能从末尾添加
 * 添加最少的字符，使得该字符串成为一个回文字符串
 *
 *
 *
 *
 */
public class Code02_AddShortestEnd {

    public static String shortestEnd(String s){
        if (s == null || s.length() == 0){
            return null;
        }
        char[] str = manacherString(s);
        int index = -1;
        int mostR = -1;
        int maxContains = 0;

        int[] pArray = new int[str.length];

        for (int i = 0; i < pArray.length; i++) {

            pArray[i] = mostR > i ? Math.min(pArray[index * 2 -i],mostR - i): 1;

            while (pArray[i] + i < str.length &&  i - pArray[i] >=0 ){
                if (str[pArray[i] + i] == str[i - pArray[i]]){
                    pArray[i] ++;
                }else {
                    break;
                }
            }
            if (pArray[i] + i > mostR){
                mostR = pArray[i] +i;
                index = i;
            }
            if (mostR == str.length){
                maxContains = pArray[i];
                break;
            }

        }
        char[]  res = new char[s.length() - maxContains + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length -1 -i ] = str[i*2 + 1];
        }

        return String.valueOf(res);


    }


    private static char[] manacherString(String s) {
        char[] charArray = s.toCharArray();
        char[] res = new char[charArray.length *2 +1];

        for (int i = 0; i != res.length; i++) {
            res[i] = (i&1) == 0 ? '#':charArray[i/2];
        }
        return res;

    }



    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }


}
