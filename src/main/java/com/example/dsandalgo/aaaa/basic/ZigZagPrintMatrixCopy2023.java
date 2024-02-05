package com.example.dsandalgo.aaaa.basic;

/**
 * 强化巩固矩阵的调度框架，分层打印的思想
 * 之字形打印矩阵
 */
public class ZigZagPrintMatrixCopy2023 {

    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = matrix.length;
        int endC = matrix[0].length;
        boolean fromUp = false;
        while (tC <= endC) {
            printLevel(fromUp, tR, tC, dR, dC, matrix);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dR = dR == endR ? dR : dR + 1;
            dC = dR == endR ? dC + 1 : dC;
            fromUp = !fromUp;
        }


    }

    private static void printLevel(boolean fromUp, int tR, int tC, int dR, int dC, int[][] matrix) {
        if (fromUp) {
            while (tC >= dC) {
                System.out.println(matrix[tR++][tC--]);
            }

        } else {
            while (dR >= tR) {
                System.out.println(matrix[dR--][dC++]);
            }

        }
    }
}
