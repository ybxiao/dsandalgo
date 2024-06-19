package com.example.dsandalgo.aaaa.basic;

/**
 * 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
 * 必须是正方形吗？
 * 是的！
 * (a, b)代表左上角的位置
 * (c,d) 代表右下角的位置
 * 先调整最外层，然后(a++,b++) (c--,d--)再调整内层
 * 当a和c或者（b和d）错过去的时候，代表调整结束了.
 * 最外层怎么调度呢 ？
 * 先进行分组，n*n的矩阵一共分成n-1组，
 * [#] (#) 「#」 #
 * #    #   #   #
 * #    #   #   #
 * #    #   #   #
 * 先想好调度框架，不扣细节，分层结构，一层一层转动
 */
public class RotateMatrix {
    public static void rotateMatrix(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length;
        int d = matrix[0].length;
        while (a < c) {//此处的循环条件也可以修改为 b < d
            rotate(matrix, a++, b++, c--, d--);
        }

    }

    public static void rotate(int[][] matrix, int a, int b, int c, int d) {
        int temp = 0;
        for (int i = 0; i < d - b; i++) {
            temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c -i][b]  = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = temp;
        }


    }


}
