package com.example.dsandalgo.class09;

import java.util.List;
import java.util.PriorityQueue;

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

    public static int bestArrage1(List<Program> list){
        if (list ==null || list.size() ==0){
            return 0;
        }






        return 0;

    }

}
