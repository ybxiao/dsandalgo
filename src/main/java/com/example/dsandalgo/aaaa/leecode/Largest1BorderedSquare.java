package com.example.dsandalgo.aaaa.leecode;

/**
 * 给定一个只有0和1组成的二维数组，返回边框全是1（内部无所谓）的最大正方形面积
 * https://leetcode.com/problems/largest-1-bordered-square/
 * 解本道题主要是运用数据预处理的技巧
 * 类似的还有前缀数组。一样的思想！ 思想！！
 */
public class Largest1BorderedSquare {

    public static int largest1BorderedSquare(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;

        int[][] right = new int[row][col];
        int[][] down = new int[row][col];
        setBordMap(m, right, down);
        int max = Integer.MIN_VALUE;
        for (int size = 1; size <= Math.min(row, col); size--) {
            if (hasSizeOfBorder(size, m, right, down)) {
                return Math.max(max, size * size);
            }

        }

        return max;
    }

    private static boolean hasSizeOfBorder(int size, int[][] m, int[][] right, int[][] down) {
        for (int i = 0; i < m.length - size + 1; i++) {
            for (int j = 0; j < m[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size
                        && down[i][j + size] >= size
                        && right[i + size][j] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void setBordMap(int[][] m, int[][] right, int[][] down) {
        int row = m.length;
        int col = m[0].length;
        if (m[row - 1][col - 1] == 1) {
            right[row - 1][col - 1] = 0;
            down[row - 1][col - 1] = 0;
        }
        for (int c = col - 2; c >= 0; c--) {
            if (m[row - 1][c] == 1) {
                down[row - 1][c] = 1;
                right[row - 1][c] = right[row - 1][c + 1] + 1;
            }
        }
        for (int r = row - 2; r >= 0; r--) {
            if (m[r][col - 1] == 1) {
                down[r][col - 1] = down[r + 1][col - 1];
                right[r][col - 1] = 0;
            }
        }
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i + 1][j] + 1;
                    down[i][j] = down[i][j + 1] + 1;
                }

            }

        }
    }
}
