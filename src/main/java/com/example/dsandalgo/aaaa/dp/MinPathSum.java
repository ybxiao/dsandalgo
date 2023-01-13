package com.example.dsandalgo.aaaa.dp;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class MinPathSum {

    //dp[i][j]代表的含义是，从左上角出发，到达dp[i][j]的最小距离累加和
    public static int minPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            //dp[0][i] = matrix[0][i] + dp[0][i - 1];
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i - 1];
            //dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }

        }
        return dp[n - 1][m - 1];

    }

    //动态规划的数组压缩技巧
    public static int minPathSum2(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dp = new int[cols];
        dp[0] = matrix[0][0];
        for (int i = 1; i < cols; i++) {
            dp[i] = dp[i-1] + matrix[0][i];
        }
        for (int i = 1; i < rows; i++) {
            dp[0] = dp[0] + matrix[i][0];
            for (int j = 1; j < cols; j++) {
                dp[j] = Math.min(dp[j] , dp[j-1]) + matrix[i][j];
            }
        }

        return dp[cols-1];
    }

    public static int[][] generateRandomMatrix(int rowSize,int colSize){
        if (rowSize < 0 || colSize <0 ){
            return null;
        }
        int[][] matrix = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                matrix[i][j] = (int)(Math.random() * 100);
            }
        }
        return matrix;
    }


    public static void main(String[] args) {
        int rowSize =10;
        int colSize =10 ;
        int[][] ints = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum(ints));
        System.out.println(minPathSum2(ints));
    }
}
