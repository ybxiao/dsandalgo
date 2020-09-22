package com.example.dsandalgo.camp01.class04;


/**
 *
 * 经典字符串匹配算法
 * KMP
 *
 *
 */
public class Code01_KMP {


    public static int getIndexOf(String s ,String m){
        if (s == null  || m  == null  || m.length() > s.length() || m.length() < 1){
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();

        //获取match数组0...index 上前缀和后缀的最大匹配长度
        int[] next  = getNextArray(match);
        int cur =  0;
        int mCur =  0;
        while (cur < str.length && mCur < match.length){
            if (str[cur] == match[mCur]){
                cur ++;
                mCur ++;
            }else if (next[mCur] >= 0){
                mCur = next[mCur];
            }else{
                mCur = 0;
            }

        }
        return mCur == match.length ? cur - mCur : -1;









    }

    //求match串的next数组
    //next数组中每一个位置上都代表0..到这个字符的前缀和后缀的最长匹配子串
    private static int[] getNextArray(char[] match) {
        int n  =  match.length  ;
        int[] next = new int[n];
        next[0] = -1;
        next[1] = 0;
        int cur  = 2;
        int cn = 0;
        while (cur  < n){
            if (match[cur] ==  next[cn]){
                next[cur++] = ++cn;
            }else if (cn > 0) {
                cn = next[cn];
            }else{
                next[cur ++] = 0;
            }


        }
        return next;




    }
}
