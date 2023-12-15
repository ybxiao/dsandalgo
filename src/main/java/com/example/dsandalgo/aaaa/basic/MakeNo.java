package com.example.dsandalgo.aaaa.basic;

/**
 * 生成长度为size的达标数组，什么叫达标？对于任意的i<k<j，
 * * 满足[i]+[j]!=[k]*2。给定一个正数size，返回长度为size的达标数组
 */
public class MakeNo {


    // 生成长度为size的达标数组
    // 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for (int i = 0; i < halfSize; i++) {
            ans[index] = base[i] * 2;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2 + 1;

        }
        return ans;
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            printArray(makeNo(i));
        }
    }
}
