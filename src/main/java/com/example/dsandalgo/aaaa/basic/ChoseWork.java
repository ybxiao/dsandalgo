package com.example.dsandalgo.aaaa.basic;

import java.util.*;

/**
 * 给定数组hard和money，长度都为N，hard[i]表示i号工作的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力，每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 */
public class ChoseWork {


    /**
     * O(N^2)
     * @param hard
     * @param money
     * @param ability
     * @return
     */
    public static int[] getBestIncome1(int[] hard, int[] money, int[] ability) {
        int[] incomes = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            for (int j = 0; j < hard.length; j++) {
                if (ability[i] >= hard[i]){
                    incomes[i] = Math.max(incomes[i], money[j]);
                }

            }
        }
        return incomes;

    }

    public static int[] bestIncomes2(int[] hard, int[] money, int[] ability){
        Job[] jobs = new Job[hard.length];
        for (int i = 0; i < hard.length; i++) {
            jobs[i] = (new Job(hard[i], money[i]));
        }
        Arrays.sort(jobs, new JobComparator()   );
        //key : hard, value: money
        TreeMap<Integer, Integer> jobMap= new TreeMap<>();
        jobMap.put(jobs[0].hard, jobs[0].money);
        Job pre = jobs[0];
        for (int i = 1; i < jobs.length; i++) {
            if (jobs[i].hard != pre.hard  &&  jobs[i].money > pre.hard ){
                pre = jobs[i];
                jobMap.put(jobs[i].hard, jobs[i].money);
            }
        }
        int[] res = new int[ability.length];
        for (int i = 0; i < ability.length ; i++) {
            Integer key = jobMap.floorKey(ability[i]);
            res[i] = key == null ? 0 : jobMap.get(key);
        }
        return res;


    }

    public static class Job{
        public int hard;
        public int money;
        
        public Job(int hard, int money){
            this.hard = hard;
            this.money = money;
        }

    }
    public static class JobComparator implements Comparator<Job>{
        @Override
        public int compare(Job j1, Job j2) {
            return j1.hard != j2.hard ? (j1.hard - j2.hard ) : j2.money - j1.money  ;
        }
    }


}
