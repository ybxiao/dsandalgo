package com.example.dsandalgo.foundationclass.class11;

/**
 * N皇后问题
 *
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。  n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 *
 *
 *
 */
public class Code09_NQueens {
    public static int num1(int n){
        if (n < 1) {
            return 0;
        }

        int[] record = new int[n];

        return process1(0,record,n);


    }

    /**
     *
     * 假设从0..到index位置上已经摆放完毕，且满足条件
     * 现在在index位置上做决策
     * 已经放皇后的位置记录在record里面，数组的下标代表行数，
     * 数值代表在哪一列上放了皇后
     *
     *
     * @param index
     * @param record
     * @param n
     * @return
     */
    private static int process1(int index, int[] record, int n) {
        //index 从0开始，当index == n的时候，代表0..n-1 N个皇后都已经放置完毕
        //此时代表当前方案有效，返回 1
        if (index == n){
            return 1;
        }
        //代表此刻index上做完决策之后，后续位置上继续放置皇后满足条件的方案数目
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (isValid(index,i,record)){
                record[index] = i;
                res += process1(index+1,record,n);
            }
        }

        return res;

    }

    private static boolean isValid(int index, int i, int[] record) {
        for (int j = 0; j < record.length; j++) {
            if (i== record[j] || Math.abs(record[j] -j) == Math.abs(index -i)){
                return false;
            }
        }
        return true;

    }


    public static int num2(int n){
        //N皇后问题请不要超过32，要不然计算机算力有限
        if (n<1 || n > 32){
            return 0;
        }

        int limit = n == 32? -1 :(1 << n) -1;

        return process2(limit,0,0,0);

    }

    private static int process2(int limit, int colLimit, int leftLimit, int rightLimit) {

        if (colLimit ==limit){
            return 1;
        }
        int pos =limit & ~(colLimit | leftLimit | rightLimit);
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0){
            mostRightOne = pos&(~pos+1);
            pos = pos -  mostRightOne;
            res += process2(limit,
                    colLimit|mostRightOne,
                    leftLimit|mostRightOne <<1,
                    rightLimit |mostRightOne >>>1
                    );

        }
        return res;
    }

    public static void main(String[] args) {

    }


}
