package com.example.dsandalgo.aaaa.basic;

/**
 * 斐波拉比数列问题
 */
public class FibonacciProblem {

    //递归版本
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }


    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        int pre = 1;
        int prePre = 1;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = pre + prePre;
            prePre = pre;
            pre = res;
        }

        return res;
    }


    //求x的n次方
    public static int maxPower(int x, int n) {
        int p = n;

        int res = 1;
        while (p != 0) {

            if ((p & 1) != 0) {
                res *= x;
            }
            x = x * x;
            p = p >> 1;
        }
        return res;
    }


}
