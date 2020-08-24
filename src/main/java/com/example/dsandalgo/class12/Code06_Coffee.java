package com.example.dsandalgo.class12;

/**
 * part 1
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间 数组有序
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 返回让所有咖啡杯变干净的最早完成时间
 * 三个参数：int[] arr、int a、int b
 *
 *
 *
 *
 */
public class Code06_Coffee {

    public static int minTime(int[] arr,int a ,int b){
        if (a > b){
            return arr[arr.length-1] +b;
        }

        return process(arr,a,b,0,0);
    }



    //0..index位置上的咖啡杯已经洗完了
    //对index位置上做决策，返回洗完index...位置上的咖啡杯所需要的最少时间。
    //washLine 代表的是此时index位置上咖啡机可用的时间点
    //drinks a, b 固定参数
    //主函数调用 process(arr,a,b,0,0)
    public static int process(int[] drinks,int a,int b,int index,int washLine){
        if (index == drinks.length-1){
            return Math.min(drinks[drinks.length-1] +b,washLine+a);
        }

        //决定机器洗
        //wash 代表用当前咖啡杯用咖啡机 所需要的时间
        int wash  = Math.max(washLine,drinks[index]) +a;
                int next1 = process(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash,next1);
        //决定挥发
        int dry = drinks[index] +b;
        int next2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(dry,next2);

        return Math.min(p1,p2);

    }

    public static int dp(int[] drinks,int a,int b){
        if (a > b){
            return drinks[drinks.length-1] +b;
        }
        int limit = 0;
        for (int i = 0; i < drinks.length; i++) {
            limit = drinks[i] +a;
        }
        int n = drinks.length;
        int[][] dp = new int[n][limit+1];
        for (int i = 0; i < limit+1; i++) {
            dp[n-1][i]  = Math.min(drinks[n-1] +b,i+a);
        }
        for (int index = n-2; index >=0 ; index--  ) {
            for (int washLine  = 0; washLine <limit+1 ; washLine++) {

                //决定机器洗
                //wash 代表用当前咖啡杯用咖啡机 所需要的时间
                int wash  = Math.max(washLine,drinks[index]) +a;
                int p1 = Integer.MAX_VALUE;
                if (wash <= limit){
                    p1 = dp[index+1][wash];
                }
                p1 = Math.max(wash,p1);
                //决定挥发
                int p2 = Math.max(drinks[index] + b, dp[index+1][washLine]);


                dp[index][washLine] = Math.min(p1,p2);
            }
        }

        return dp[0][0];

    }
}
