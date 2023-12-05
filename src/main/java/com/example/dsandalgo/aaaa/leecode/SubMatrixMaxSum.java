package com.example.dsandalgo.aaaa.leecode;

/**
 * 返回一个二维数组中子矩阵最大累加和
 * https://leetcode-cn.com/problems/max-submatrix-lcci/
 * <p>
 * 问题的关键是把求解过程给拆分，枚举所有的矩阵，然后看那个矩阵的累加和最大，记录其为位置信息
 */
public class SubMatrixMaxSum {

    public static int getMaxSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int[] k = new int[M];
            for (int j = i; j < N; j++) {
                for (int l = 0; l < M; l++) {
                    k[l] += matrix[j][l];
                    max = Math.max(max, SubArrayMaxSum.maxSubArray(k));

                }
            }
        }
        return max;

    }

    public static int getMaxSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    s[k] += matrix[j][k];
                    cur += s[k];

                    if (max < cur) {
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k + 1;
                    }

                }

            }
        }

        return max;


    }
}
