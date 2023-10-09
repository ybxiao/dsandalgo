package com.example.dsandalgo.aaaa.basic;

/**
 * 给定一个二维数组matrix，你可以从任何位置出发，走向上、下、左、右四个方向，返回能走出来的最长的递增链长度
 */
public class LongestIncreasingPath {

    public static int longestIncreasingPath1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int longest = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                longest = Math.max(longest, process(i, j, matrix, row, col));
            }

        }
        return longest;


    }

    public static int process(int i, int j, int[][] matrix, int row, int col) {
        int res = 0;
        if (j > 0 && matrix[i][j] < matrix[i][j - 1]) {
            res = Math.max(res, process(i, j - 1, matrix, row, col) + 1);
        }
        if (i > 0 && matrix[i][j] < matrix[i - 1][j]) {
            res = Math.max(res, process(i - 1, j, matrix, row, col));
        }
        if (i < row - 1 && matrix[i][j] < matrix[i + 1][j]) {
            res = Math.max(res, process(i + 1, j, matrix, row, col));
        }
        if (j < col - 1 && matrix[i][j] < matrix[i][j + 1]) {
            res = Math.max(res, process(i, j + 1, matrix, row, col));
        }

        return res;
    }


    public static int longestIncreasingPath2(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row ; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(ans, process2(i,j,matrix,row, col, dp));
            }

        }
        return ans;
    }

    public static int process2(int i, int j, int[][] matrix, int row, int col, int[][] dp){
        if (dp[i][j] != 0){
            return dp[i][j];
        }
        int res = 0;
        if (j > 0 && matrix[i][j] < matrix[i][j - 1]) {
            res = Math.max(res, process(i, j - 1, matrix, row, col) + 1);
        }
        if (i > 0 && matrix[i][j] < matrix[i - 1][j]) {
            res = Math.max(res, process(i - 1, j, matrix, row, col));
        }
        if (i < row - 1 && matrix[i][j] < matrix[i + 1][j]) {
            res = Math.max(res, process(i + 1, j, matrix, row, col));
        }
        if (j < col - 1 && matrix[i][j] < matrix[i][j + 1]) {
            res = Math.max(res, process(i, j + 1, matrix, row, col));
        }
        dp[i][j] = res;
        return dp[i][j];
    }





}
