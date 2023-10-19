package com.example.dsandalgo.aaaa.dp;

import javax.naming.ldap.LdapReferralException;
import java.math.BigInteger;
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
public class CoffeeCopy2023 {

    /**
     * 暴力方法，做对数器用
     *
     * @param arr
     * @param n
     * @param a
     * @param b
     * @return
     */
    public static int right(int[] arr, int n, int a, int b) {
        //每个咖啡机可用的时间
        int[] times = new int[arr.length];
        //每个杯子可洗的时间点
        int[] drinks = new int[n];

        return forceMake(arr, times, 0, drinks, n, a, b);

    }

    /**
     * 每一个人，暴力尝试用每一个咖啡器制作给自己做咖啡，最后所有杯子变干净的耗时
     *
     * @param arr
     * @param times  表示咖啡机可用的时间
     * @param kth
     * @param drinks
     * @param n
     * @param a
     * @param b
     * @return kth..n这么多个人，喝完咖啡且挥发干净的最短时间
     */
    private static int forceMake(int[] arr, int[] times, int kth, int[] drinks, int n, int a, int b) {
        if (kth == n) {
            int[] drinksCopy = Arrays.copyOf(drinks, kth);
            Arrays.sort(drinksCopy);
            return forceWash(drinksCopy, 0, a, b, 0, 0);
        }
        int all = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drinks[kth] = pre + work;
            times[i] = pre + work;
            all = Math.min(all, forceMake(arr, times, kth + 1, drinks, n, a, b));
            drinks[kth] = 0;
            times[i] = pre;

        }
        return all;


    }

    /**
     * @param drinks   所有的杯子的可洗时间
     * @param index    当前洗到index号杯子
     * @param a        洗咖啡机洗完一个杯子所用的时间
     * @param b        自然挥发一个杯子所消耗的时间
     * @param washLine 洗咖啡机可用的时间
     * @param times    总时间
     * @return index...n所有杯子变干净的总最低时间
     */
    private static int forceWash(int[] drinks, int index, int a, int b, int washLine, int times) {
        if (index == drinks.length) {
            return times;
        }

        int wash = Math.min(drinks[index], washLine) + a;
        int p1 = forceWash(drinks, index + 1, a, b, wash, Math.max(wash, times));

        int dry = drinks[index] + b;
        int p2 = forceWash(drinks, index + 1, a, b, washLine, Math.max(dry, times));

        return Math.min(p1, p2);
    }


    public static class Machine {
        public int workTime;
        public int timePoint;

        public Machine(int work, int timePoint) {
            workTime = work;
            timePoint = timePoint;
        }

    }

    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.workTime + o1.timePoint) - (o2.workTime + o2.timePoint);
        }
    }

    public static int minTime1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(arr[i], 0));
        }
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine poll = heap.poll();
            drinks[i] = poll.timePoint + poll.workTime;
            heap.add(new Machine(poll.workTime, drinks[i]));
        }
        return bestTime1(drinks, a, b, 0, 0);

    }

    /**
     * @param drinks   所有的杯子的可洗时间
     * @param index    当前洗到index号杯子
     * @param a        洗咖啡机洗完一个杯子所用的时间
     * @param b        自然挥发一个杯子所消耗的时间
     * @param washLine 洗咖啡机可用的时间
     * @return index...n所有杯子变干净的总最低时间
     * 比起forceWash 少了一个可变参数，这样在推导动态规划的严格位置依赖时，难度就从三维降成了二维
     */
    private static int bestTime1(int[] drinks, int a, int b, int index, int washLine) {
        if (index == drinks.length) {
            return 0;
        }
        // 第一个决策，决定当前杯子用机器洗
        int selfWash = Math.max(drinks[index], washLine) + a;
        int nextWash = bestTime1(drinks, a, b, index + 1, selfWash);
        int p1 = Math.max(selfWash, nextWash);

        //第二个决策，决定当前杯子挥发
        int selfClean = drinks[index] + b;
        int nextClean = bestTime1(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(selfClean, nextClean);

        return Math.min(p1, p2);
    }


    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int maxFree = 0;
        int n = drinks.length;
        for (int i = 0; i < n; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + wash;
        }
        int[][] dp = new int[n + 1][maxFree + 1];
        for (int index = n - 1; index >= 0; index++) {
            for (int time = 0; time <= maxFree; time++) {


                int selfClean1 = Math.max(drinks[index], time) + wash;
                //超出动态规划的缓存表，后续就不用计算了
                if (selfClean1 > maxFree) {
                    break;
                }
                int nextClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, nextClean1);

                int selfClean2 = drinks[index] + air;
                int nextClean2 = dp[index][time];
                int p2 = Math.max(selfClean2, nextClean2);

                dp[index][time] = Math.min(p1, p2);


            }

        }

        return dp[0][0];

    }

    public static void main(String[] args) {
        int t = 1234234235;
        System.out.println(t << 16);
        System.out.println((11 | (1 << (RESIZE_STAMP_BITS - 1))) << RESIZE_STAMP_SHIFT);
    }

    private static int RESIZE_STAMP_BITS = 16;
    private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;


    public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6
        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) {
            n += 16;
            i <<= 16;
        }
        if (i >>> 24 == 0) {
            n += 8;
            i <<= 8;
        }
        if (i >>> 28 == 0) {
            n += 4;
            i <<= 4;
        }
        if (i >>> 30 == 0) {
            n += 2;
            i <<= 2;
        }
        n -= i >>> 31;
        return n;
    }


}
