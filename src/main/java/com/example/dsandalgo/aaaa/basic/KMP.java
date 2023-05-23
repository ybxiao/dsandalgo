package com.example.dsandalgo.aaaa.basic;

/**
 * kmp算法
 * a) 给一个字符串，对于每一个i位置的字符，都可以有一个值：从0...i-1位置字符所组成的字符串前缀和后缀的最长公共子串，不包含所有，作为i的next值。
 * b)
 */
public class KMP {


    public static int getIndexOf(String str , String match){
        if (str.length() == 0 || match.length() ==0 || match.length() > str.length()){
            return -1;
        }
        char[] strChar = str.toCharArray();
        char[] matchChar = match.toCharArray();
        int[] next = getNextArray(matchChar);
        int i  = 0;
        int j = 0;
        while (i < strChar.length && j < matchChar.length){
            if (strChar[i] == matchChar[j]){
                i++;
                j++;
            }else if (next[j] == -1){
                i++;
            }else{
               j = next[j];
            }
        }

        return  j == matchChar.length ? i -j : -1;



    }


    /**
     *
     * a b c e 1 2 3 2 1
     *
     * @param match
     * @return
     */
    //对于每一个位置i而言，求从0...i-1位置所组成的字符串的前缀和后缀的最长公共子串长度，不包含所有。
    public static int[] getNextArray(char[] match){
        if (match.length == 1 ){
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int  i = 2;
        //cn代表的含义是什么？ 表示哪个位置上的值在和match[i-1]进行比较
        int  cn = 0;
        while (i < match.length) {
            if (match[cn] == match[i-1]){
                next[i++] = ++cn;
            }else if (cn > 0){
                //cn = next[i-1];
                cn = next[cn];
            } else {
               next[i++] = 0;
            }
        }

        return next;
    }


    public static String getRandomString(int possibilities, int size){
        char[] chars = new char[(int) (Math.random() * size + 1)];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ( (int)(Math.random() * possibilities + 1) + 'a');
        }
        return String.valueOf(chars);

    }

    public static void main(String[] args) {
        /*int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match  =  getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) !=  str.indexOf(match)){
                System.out.println("Oops!");
            }
        }
        System.out.println("finished");*/
        System.out.println(Integer.bitCount(-1));
        System.out.printf( Integer.toBinaryString(-1));



    }


}
