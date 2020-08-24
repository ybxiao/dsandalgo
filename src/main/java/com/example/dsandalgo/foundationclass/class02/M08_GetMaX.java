package com.example.dsandalgo.foundationclass.class02;

public class M08_GetMaX {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int b, int e) {
        if (b == e) {
            return arr[b];
        }
        int mid = b + (e - b) >> 2;
        int l1 = process(arr, b, mid);

        int l2 = process(arr, mid + 1, e);
        return Math.max(l1, l2);
    }

}
