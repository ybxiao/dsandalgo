package com.example.dsandalgo.aaaa.dp;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class NQueensCopy2023 {

    public static int nQueens(int num) {
        if (num <= 3) {
            return 0;
        }
        //record[i] = j 标识在第i行 第j列放置了n皇后
        int[] record = new int[num];
        return process(record, 0, num);
    }

    // 0...index位置上已经放置好了皇后
    //返回从第index行放置，一直放到第n，不同行不同列的摆法有多少种
    private static int process(int[] record,int index, int n) {
        //能摆放到越界位置，此时返回一种方法
        if (index == n){
            return 1;
        }else{
            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (valid(record, index ,i)){
                    record[index] = i;
                    ans += process(record, index + 1, n);
                }

            }


            return ans;

        }
    }

    private static boolean valid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j){
                return false;
            }
        }
        while (i >=0 && j < record.length){
            if (record[i--] == j++){
                return false;
            }
        }
        while (i>=0 && j >=0) {
            if (record[i--] == j--) {
                return false;
            }
        }
        return true;
    }

}

