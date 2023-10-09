package com.example.dsandalgo.aaaa.basic;

/**
 * 螺旋打印一个矩阵
 */
public class PrintMatrixSpiralOrder {

    public static void printMatrixSpiralOrder(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length;
        int dC = matrix[0].length;
        while (tR <= dR) {
            printLevel(matrix, tR++, tC++, dR--, dC--);
        }
    }

    private static void printLevel(int[][] matrix, int tR, int tC, int dR, int dC) {
        if (tR == dR) {
            while (tC <= dC) {
                System.out.println(matrix[tR][tC++] + " ");
            }
        } else if (tC == dC) {
            while (tR <= dR) {
                System.out.println(matrix[tR++][tC] + " ");
            }
        } else {
            int curTR = tR;
            int curTC = tC;

            while (curTC != dC) {
                System.out.println(matrix[tR][curTC] + " ");
                curTC++;
            }

            while (curTR != dR) {
                System.out.println(matrix[curTR][dC] + " ");
                curTR++;
            }

            while (curTC != tC) {
                System.out.println(matrix[dR][curTC] + " ");
                curTC--;
            }

            while (curTR != tR) {
                System.out.println(matrix[curTR][tC] + " ");
                curTR--;
            }

        }

    }


}
