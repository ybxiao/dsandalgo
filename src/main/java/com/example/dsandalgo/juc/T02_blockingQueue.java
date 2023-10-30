package com.example.dsandalgo.juc;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class T02_blockingQueue {

    static AtomicInteger threadNo = new AtomicInteger(1);
    public static volatile int index = 1;


    public static void main(String[] args) {

        char[] char1 = "1234567".toCharArray();
        char[] chars2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
//            for (char c: char1) {
//                while (threadNo.get() == 1){
//                    System.out.println(c);
//                    threadNo.set(2);
//                }
//
//
//            }

            for (char c : char1) {
                /*while (threadNo.get() != 1){

                }*/
                while (index != 1) {

                }
                System.out.println(c);
                //threadNo.set(2);
                index = 2;

            }


        }, "t1").start();


        new Thread(() -> {
//            for (char c: chars2) {
//                while (threadNo.get()==2){
//                    System.out.println(c);
//                    threadNo.set(1);
//                }
//
//
//            }
            for (char c : chars2) {
                /*while (threadNo.get() !=2){

                }*/
                while (index != 2) {

                }
                System.out.println(c);
                //threadNo.set(1);
                index = 1;

            }


        }, "t2").start();


    }


}
