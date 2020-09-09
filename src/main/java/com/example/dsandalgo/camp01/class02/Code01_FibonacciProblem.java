package com.example.dsandalgo.camp01.class02;

/**
 * 斐波拉比数列问题
 *
 * 1 暴力递归求解 O（2^N)
 * 2 线性求解 O(N)
 * 3 利用快速幂求解 O(logN)
 *
 *
 * 基于斐波拉比数列的推广
 *
 */
public class Code01_FibonacciProblem {

    //暴力递归求解
    public static int f1(int N){
       if (N < 0){
           return -1;
       }
       if (N == 1 || N ==2){
           return 1;
       }
       return f1(N-1)+f1(N-2);
    }


    //线性求解
    public static int f2(int N){
        if (N < 0){
            return  -1;
        }
       /* int[] arr = new int[N+1];
        for (int i = 3; i <=N ; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }

        return arr[N];*/
       if (N ==1 || N ==2){
           return 1;
       }

       int ans = 0;
       int pre = 1;
       int prePre =  1;
        for (int i = 3 ; i <=N ; i++) {
            ans = pre + prePre;
            prePre =  pre;
            pre = ans;


        }

        return ans;

    }

    //快速幂
    public static int f3(int N){
        if (N < 0){
            return  -1;
        }
        if(N == 1 || N == 2){
            return 1;
        }

        int[][] base = {
                {1,1},
                {1,0}
        };

        int[][] res =  matrixPower(base,N);
        return res[0][0] + res[1][0];


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
