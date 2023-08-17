package com.example.dsandalgo.aaaa.basic;

/**
 * kmp算法
 * a) 给一个字符串，对于每一个i位置的字符，都可以有一个值：从0...i-1位置字符所组成的字符串前缀和后缀的最长公共子串，不包含所有，作为i的next值。
 * b)
 */
public class KMP {


    public static int indexOf(String source, String match) {
        if (source == null || match == null || source.length() < match.length()) {
            return 0;
        }
        char[] sourceCharArray = source.toCharArray();
        char[] matchCharArray = match.toCharArray();
        int[] nextArray = getNextArray(matchCharArray);
        int i = 0;
        int j = 0;
        while (i < source.length() && j < match.length()){
            if (sourceCharArray[i] == matchCharArray[j]){
                i++;
                j++;
            }else if (nextArray[j] >= 0){
               j = nextArray[j];
            }else{
                i++;
            }
        }

        return j == match.length() ? j -i : 0;

    }


    //生成匹配串的next数组
    public static int[] getNextArray(char[] match) {
        int n = match.length;
        int[] nextArray = new int[n];
        nextArray[0] = -1;
        nextArray[1] = 0;
        int cn = 0;

        for (int index = 2; index < n; index++) {
            if (match[index - 1] == match[cn]) {
                nextArray[index++] = ++cn;
            } else if (cn > 0) {
                cn = nextArray[cn];
            } else {
                nextArray[index++] = 0;
            }

        }
        return nextArray;
    }


    public static String getRandomString(int possibilities, int size) {
        char[] chars = new char[(int) (Math.random() * size + 1)];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((int) (Math.random() * possibilities + 1) + 'a');
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
        System.out.printf(Integer.toBinaryString(-1));


    }


}
