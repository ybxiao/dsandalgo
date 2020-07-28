package com.example.dsandalgo.class09;

import java.util.*;

/**
 * 会议安排
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 *
 *
 * 1 递归
 * 2 贪心
 *
 */
public class Code04_BestArrange {

    public static class Program{
        private int start;
        private int end;

        public Program(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrage1(Program[] arr ){
        if (arr ==null || arr.length==0){
            return 0;
        }
        int timeEnd =0;
        int max = process(arr,0,timeEnd);
        return 0;

    }

    private static int process(Program[] arr, int num, int timeEnd) {
        if (arr.length == 0){
            return num;
        }else{
            int max = num;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].start >= timeEnd){
                    Program[] next =copyButExcept(arr,i);
                    int process = process(next, num + 1, arr[i].end);
                    max= Math.max(process,max);
                }
            }
            return max;
        }
    }

    private static Program[] copyButExcept(Program[] arr, int i) {
        Program[] ans = new Program[arr.length -1];
        int index = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j !=i){
                ans[index++] = ans[j];
            }
        }
        return ans;
    }


    public static int bestArrange2(Program[] arr){
        if (arr == null || arr.length ==0){
            return 0;
        }
        Arrays.sort(arr,new MyComparator());
        int ans = 0;
        int timeEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start >timeEnd){
                ans ++;
                timeEnd = arr[i].end;
            }
        }


        return ans;
    }


    public static class MyComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

}
