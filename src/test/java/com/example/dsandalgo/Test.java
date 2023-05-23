package com.example.dsandalgo;


import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class Test {
    public static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 2;
        swap(a, b);
        System.out.println("a = " + a + " b= " + b);
    }
}
