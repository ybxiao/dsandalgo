package com.example.dsandalgo.aaaa.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 */
public class Coffee {

    //暴力尝试的方法
    public static int right(int[] arr, int n, int a, int b) {
        //每一个咖啡机可用的时间，最开始是全部在0时可用
        int[] times = new int[arr.length];
        //每一个人喝完的时间
        int[] drinks = new int[n];
        return forceMake(arr, times, 0, drinks, n, a, b);
    }

    /**
     * 暴力尝试，让每一个人尝试使用每一台咖啡机制作咖啡
     *
     * @param arr    每一台咖啡机制作一杯咖啡需要的时间
     * @param times  每一台咖啡机当前可用的时间
     * @param index  当前轮到编号index人来喝咖啡了，尝试选择每一台咖啡机来制作咖啡
     * @param drinks 每一个人咖啡喝完的时间点
     * @param n      一共n个人
     * @param a      洗一个杯子需要的时间
     * @param b      自然挥发一个杯子需要的时间
     * @return 编号0...index-1的人都已经喝完了咖啡，且喝完咖啡的时间点都放到drinks， 返回index...n这些人也喝完咖啡，且所有杯子变干净的时间
     */
    private static int forceMake(int[] arr, int[] times, int index, int[] drinks, int n, int a, int b) {
        // 说明所有的人都已经喝完了咖啡
        if (index == n) {
            int[] drinkSorted = Arrays.copyOf(drinks, index);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        } else {
            int time = Integer.MAX_VALUE;
            //分别选择使用每一台咖啡机制作咖啡
            for (int i = 0; i < arr.length; i++) {
                int pre = times[i];
                drinks[index] = pre + arr[0];
                times[i] = pre + arr[0];
                time = Math.min(time, forceMake(arr, times, index + 1, drinks, n, a, b));
                drinks[index] = 0;
                times[i] = pre;
            }

            return time;
        }

    }

    /**
     * 从左往右的尝试，0...index-1的杯子都已经洗干净了。返回index...之后的杯子变干净的最早时间
     *
     * @param drinkSorted 所有咖啡杯就绪的时间
     * @param a           咖啡机洗咖啡杯的时间
     * @param b           咖啡杯自然挥发的时间
     * @param index       当前咖啡杯编号，洗到了哪个咖啡杯
     * @param washLine    洗咖啡机可用的时间
     * @param times       0...index-1号杯子变干净的最早时间
     * @return
     */
    private static int forceWash(int[] drinkSorted, int a, int b, int index, int washLine, int times) {
        if (index == drinkSorted.length) {
            return times;
        }
        //1 选择使用咖啡机洗
        int wash = Math.max(drinkSorted[index], washLine) + a;
        int p1 = forceWash(drinkSorted, a, b, index + 1, wash, Math.max(wash, times));
        //2 选择自然挥发
        int dry = drinkSorted[index] + b;
        int p2 = forceWash(drinkSorted, a, b, index + 1, washLine, Math.max(dry, times));
        return Math.min(p1, p2);
    }

    //贪心解法。
    public static class Machine {
        private int timePoint;
        private int workTime;

        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }

    }

    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.workTime + o1.timePoint) - (o2.workTime + o2.timePoint);
        }
    }

    /**
     * @param arr 咖啡机制作一杯咖啡分别所需要的时间
     * @param n   一共有n个人和咖啡
     * @param a   洗一个杯子所用的时间
     * @param b   自然挥发一个杯子干净所用的时间
     * @return
     */
    public static int minTime1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> machinesHeap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            machinesHeap.add(new Machine(arr[i], 0));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine poll = machinesHeap.poll();
            poll.timePoint = poll.workTime + poll.timePoint;
            drinks[i] = poll.timePoint;
            machinesHeap.add(poll);
        }
        return bestTime1(drinks, a, b, 0, 0);
    }

    /**
     * @param drinks 咖啡喝完的时间，即每一个咖啡杯可以清洗的时间
     * @param a      用洗咖啡杯机器清洗的时间
     * @param b      自然挥发的时间
     * @param index
     * @param free   洗杯机可用的时间
     * @return drinks[index ... ] 之后所有的杯子变干净的最早时间
     */
    private static int bestTime1(int[] drinks, int a, int b, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        //两种可能性讨论
        //1  index号杯子选择挥发
        int currentH = drinks[index] + b;
        int leftClean1 = bestTime1(drinks, a, b, index + 1, free);
        int p1 = Math.max(currentH, leftClean1);
        //2  index号杯子选择机器洗
        int curWash = Math.max(drinks[index], free) + a;
        int leftClean2 = bestTime1(drinks, a, b, index + 1, curWash);
        int p2 = Math.max(curWash, leftClean2);
        return Math.min(p1, p2);
    }


    /**
     * 使用bestTime1来改对应的dp版本
     *
     * @param drinks 所有待清洗的杯子列表，以及每个杯子可清洗的时间
     * @param wash   机器清洗杯子的时间
     * @param air    杯子自由挥发的时间
     * @return
     */
    private static int bestTimeDp(int[] drinks, int wash, int air) {
        int n = drinks.length;
        //通过业务限制，寻找业务参数的边界
        int maxFree = 0;
        for (int i = 0; i < n; i++) {
            maxFree = Math.max(drinks[i], maxFree) + wash;
        }
        int[][] dp = new int[n + 1][maxFree + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int free = maxFree; free >= 0; free--) {
                int selfClean1 = Math.max(drinks[index], free) + wash;
                if (selfClean1 > maxFree) {
                    break;
                }
                //1  决定洗
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);
                //2 决定挥发
                int selfClean2 = drinks[index] + air;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);


            }

        }
        return dp[0][0];
    }


}
