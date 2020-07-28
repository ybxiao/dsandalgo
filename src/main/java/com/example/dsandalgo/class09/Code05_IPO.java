package com.example.dsandalgo.class09;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * created on 2020/7/28.
 * time: 17:13
 * 项目投资算法
 * @author yibo.xiao
 */


public class Code05_IPO {


    public static int findMaximizedCapital(int k,int w ,int[] costs,int[] profits){
        PriorityQueue<Program> costQueue = new PriorityQueue<>(new MyCostComparator());
        PriorityQueue<Program> profitQueue = new PriorityQueue<>(new MyProfitComparatorB());
        for (int i = 0; i < costs.length; i++) {
            costQueue.add(new Program(costs[i],profits[i]));
        }
        while (!costQueue.isEmpty()){
            while (costQueue.peek().c < k){
               profitQueue.add(costQueue.poll());
            }
            if (!profitQueue.isEmpty()){
                k = profitQueue.poll().p +k;
            }
        }

    return k;

    }

    public static  class Program{
        public int p;
        public int c;

        public Program(int p,int c){
            this.p = p;
            this.c = c;
        }
    }

    public static class MyCostComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    public static class MyProfitComparatorB implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o2.p;
        }
    }


}
