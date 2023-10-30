package com.example.dsandalgo.aaaa.dp;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * <p>
 * 从左往右的尝试模型
 */
public class Convert2LetterString {

    public static int number(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        return process(s.toCharArray(), 0);
    }

    /**
     * 函数的含义是假设从0... index -1 位置上的数字都已经完成了转化，
     * 返回从数组 index...往后的位置上，可以有多少种转化的结果。
     * index 是可变参数，代表当前的位置
     */
    private static int process(char[] str, int index) {
        //当index来到了str.length位置,
        if (index == str.length) {
            return 1;
        }
        //如果单独遇到了'0'字符，说明前面已经做得决策有误，此时无法进行转化
        if (str[index] == '0') {
            return 0;
        }
        //对于一个普遍的位置，有多少种转化的可能性
        //1) 单个数字自己进行转化
        //2) 两个位置的数字一起转化，此时需要注意，当前位置的数字 + 下一个位置的数字不能超过26，因为字母只有26个
        int p1 = process(str, index + 1);
        //index + 1 < str.length &&
        if (index + 1 < str.length && ((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
            p1 += process(str, index + 2);
        }

        return p1;
    }

    //只有一个可变参数，
    //从右往左的动态规划，根据暴力方法修改，具体思路参考方法一
    //dp[i]的含义，代表 str[i...n]位置有多少转化方式
    public static int dp1(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int index = n - 1; index >= 0; index--) {
            if (str[index] != '0'){
                dp[index] = dp[index + 1];
                if (index + 1 < n && ((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
                    dp[index] += dp[index + 2];
                }
            }

        }
        return dp[0];
    }

    //dp[i]代表从str[0...i] 位置有多少种转化方式
    //从左往右的动态规划
    public static int dp2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        char[] str = s.toCharArray();
        //边界条件一 ： 如果第一个字符为'0'，那么直接返回，无法转化
        if (str[0] == '0'){
            return 0;
        }
        int n = str.length;
        int[] dp = new int[n];

        dp[0] = 1;
        //dp[1] = (str[0] - '0') * 10 + str[1] - '0' < 27 ? 2 : 1;
        /*for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1];
            if (((str[i - 1] - '0') * 10 + str[i] - '0') < 27) {
                dp[i] += dp[i - 2];
            }

        }*/

        for (int i = 1; i < n; i++) {
            //边界条件二：以 i位置向前看考虑可能性。
            //如果i 向前看两位发生越界，此时代表转化方法只有一种
            int temp = i - 2 >= 0 ? dp[i - 2] : 1;
            if (str[i] == '0') {
                //此时如果能成功进行转化要求
                if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = temp;
                }
            } else {
                dp[i] = dp[i - 1];
                if (str[i - 1] != '0' && ((str[i - 1] - '0') * 10 + str[i] - '0') < 27) {
                    dp[i] += temp;
                }
            }
        }

        return dp[n - 1];

    }

    //构建随机数字字符串
    public static String randomString(int len) {
        char[] chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        //测试次数
        int testTimes = 100000;
        //字符串长度
        int n = 30;

        for (int i = 0; i < testTimes; i++) {
            String s = randomString((int) (Math.random() * n));
            int i1 = number(s);
            int i2 = dp1(s);
            int i3 = dp2(s);
            if (i1 != i2 || i1!=i3){
                System.out.println(s);
                System.out.println(i1);
                System.out.println(i2);
                System.out.println(i3);
                System.out.println("Oops!");
                break;
            }

        }
        System.out.println("测试结束");
    }


}
