package com.example.dsandalgo.aaaa.backtrack;

public class Test {

    public static void main(String[] args) {
        System.out.println(max("cbbd"));
        System.out.println(max("babad"));

    }

    public static String max(String source) {
        if (source == null || source.length() == 0) {
            return null;
        }
        char[] sourceArray = source.toCharArray();
        char[] targetArray = convert(sourceArray);
        int mid = 0;
        int maxLength = 0;
        int n = targetArray.length;
        for (int i = 1; i < n; i++) {
            int temp = 1;
            int left = i-1;
            int right = i +1;
            while (left > 0 && right < n - 1) {

                if (targetArray[left--] == targetArray[right++]) {
                    temp++;
                }
            }
            if (temp > maxLength) {
                maxLength = temp;
                mid = i;
            }
        }
        int realLength = maxLength  - 1;
        int leftStart = mid - maxLength ;
        return source.substring(leftStart, maxLength);


    }

    public static char[] convert(char[] sourceArray) {

        int n = sourceArray.length;
        char[] res = new char[n * 2 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '*' : sourceArray[(i) / 2 ];
        }
        return res;
    }
}
