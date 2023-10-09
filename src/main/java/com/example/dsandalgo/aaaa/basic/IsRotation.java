package com.example.dsandalgo.aaaa.basic;

public class IsRotation {

    /**
     * 旋转字符串的定义 abcde： abcde bcdea cdeab deabc eabcd
     *
     * @param a
     * @param b
     * @return a串是否是b串的旋转字符串
     */
    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || (a.length() != b.length())) {
            return false;
        }
        String c = b + b;
        return getIndexOf(c, a) != -1;

    }

    public static int getIndexOf(String s, String m) {
        int[] nextArray = nextArray(m);
        char[] sourceArray = s.toCharArray();
        char[] matchArray = m.toCharArray();
        int i = 0;
        int j = 0;
        while (i < sourceArray.length && j < matchArray.length) {
            if (sourceArray[i] == matchArray[j]) {
                i++;
                j++;
            } else if (nextArray[j] > 0) {
                j = nextArray[j];
            } else {
                i++;
            }
        }

        return j == matchArray.length ? i - j : -1;


    }

    public static int[] nextArray(String m) {
        int[] nextArray = new int[m.length()];
        char[] matchArray = m.toCharArray();
        nextArray[0] = -1;
        nextArray[1] = 0;
        int cn = 0;
        int pos = 2;
        while (pos < m.length()) {
            if (matchArray[pos - 1] == matchArray[cn]) {
                nextArray[pos++] = ++cn;
            } else if (cn > 0) {
                cn = nextArray[cn];
            } else {
                nextArray[pos++] = 0;
            }
        }


        return nextArray;
    }
}
