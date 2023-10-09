package com.example.dsandalgo.aaaa.basic;

import java.util.Random;

/**
 * ReservoirSampling
 * 蓄水池算法实现
 * 假设有一个源源吐出不同球的机器，
 * 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
 * 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里
 */
public class ReservoirSampling {

    public static class RandomBox {
        private int[] bags;
        //袋子的容量
        private int capacity;
        //元素编号
        private int count;

        public RandomBox(int n) {
            capacity = n;
            bags = new int[capacity];
            count = 0;
        }

        private static int rand(int max) {
            return (int) (Math.random() * max) + 1;
        }

        public void addItem(int item) {
            count++;
            if (count <= capacity) {
                bags[count - 1] = item;
            } else {
                if (rand(count) <= capacity) {
                    bags[rand(capacity) - 1] = item;
                }

            }
        }

        public int[] choices(){
            int[] ans = new int[capacity];
            for (int index = 0; index < capacity; index++) {
                ans[index] = bags[index];
            }
            return ans;
        }

    }


    public static void main(String[] args) {
        new Random().nextInt(10);
    }
}

