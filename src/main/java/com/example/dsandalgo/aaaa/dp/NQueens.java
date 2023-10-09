package com.example.dsandalgo.aaaa.dp;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class NQueens {
    public static int nums1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    private static int process(int index, int[] record, int n) {
        if (index == n) {
            return 1;
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            if (valid(record, index, j)) {
                record[index] = j;
                ans += process(index + 1, record, n);
            }

        }
        return ans;


    }

    private static boolean valid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(nums1(4));
    }


    public static int num2(int n) {
        if (n > 32 || n < 0) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit ,0, 0, 0  );
    }

    private static int process2(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {
        if (colLimit == limit){
            return 1;
        }
        int pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        int res = 0;
        while (pos > 0){
            int mostRightOne = pos & (~pos +1);
            pos = pos -mostRightOne;
            res += process2(limit , colLimit | mostRightOne, (leftDiaLimit | mostRightOne) <<1, (rightDiaLimit| mostRightOne) >>>1);
        }




        return res;


    }
}
