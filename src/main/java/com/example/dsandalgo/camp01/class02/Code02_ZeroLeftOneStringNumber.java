package com.example.dsandalgo.camp01.class02;


/**
 *
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 *
 * 如果某个字符串,任何0字符的左边都有1紧挨着,认为这个字符串达标
 *
 * 返回有多少达标的字符串
 *
 *
 * 三种解法：
 * 1  递归求解
 * 2  线性
 * 3 套用推广公式，快速幂
 *
 *
 */
public class Code02_ZeroLeftOneStringNumber {

    public static int getNum1(int n){
        if (n < 1){
            return -1;
        }
        return process(1,n);
    }


    //递归函数含义： 代表0...i-1位置上已经摆放完毕
    //此刻在i位置上进行决策 放0还是放1 能满足条件
    //返回值是 i...n上满足条件的方法数
    public static int process(int i ,int n){
        if (i == n-1){
            return 2;
        }
        if (i == n){
            return 1;
        }
        return process(i+1,n) + process(i+2,n);
    }


    public static int getNum2(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int temp  =  0;
        for (int i = 2; i < n + 1; i++) {
            temp = cur;
            cur =  cur + pre;
            pre = temp;
        }
        return cur;

    }


    public static int getNum3(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n ==2){
            return n;
        }
        int[][] base =  {
                {1,1},
                {1,0}
        };
        int[][] res =  matrixPower(base,n-2);

        return 2*res[0][0] + res[1][0];

    }

    //求矩阵base的N次幂 只需要计算log(N)次
    private static int[][] matrixPower(int[][] base, int n) {
        int[][] res =  new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }

        int[][] temp = base;
        for (;  n != 0 ; n >>= 1) {

            if ((n & 1) != 0){
                res = multiMatrix(res,temp);
            }
            temp =  multiMatrix(temp,temp);
        }

        return res;
    }


    //两个矩阵相乘的函数
    public static int[][] multiMatrix(int[][] m1,int [][]m2){

        int[][] ans  =  new int[m1.length][m2[0].length];

        for (int i = 0; i < m1[0].length; i++) {
            for (int j = 0; j < m2.length; j++) {
                int res =  0;
                for (int k = 0; k < m1.length; k++) {
                    res +=  m1[i][k] * m2[k][j];
                }
                ans[i][j] = res;

            }
        }

        return ans;

    }
}
