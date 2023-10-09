package com.example.dsandalgo.aaaa.basic;

/**
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
 * 如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标
 * 返回有多少达标的字符串
 * 尝试方法：
 * 从左往右开始尝试，N个字符，第一个必须为1，
 *
 */
public class ZeroLeftOneStringNumberCopy2023 {

    public static int getNum(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    /**
     * @param index 假设0..index-1 位置上0和1都已经摆放完成，此时在index上进行决策
     * @param n     固定参数
     * @return 返回index ...n 有多少达标的字符串。
     */
    public static int process(int index, int n) {
        if (index == n - 1) {
            return 2;
        }
        if (index == n) {
            return 1;
        }
        return process(index + 1, n) + process(index + 2, n);

    }

    public static int f(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        int num = getNum(5);
        int num2 = f(5);
        System.out.println(num +"---" + num2);
    }

}
