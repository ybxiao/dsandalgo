package com.example.dsandalgo.juc;

import java.util.concurrent.locks.LockSupport;

public class T02_002 {

    enum READYTORUN {T1,T2};
    //必须是volatile
    private static volatile READYTORUN r = READYTORUN.T1;

    public static void main(String[] args) {
        char[] char1 = "1234567".toCharArray();
        char[] chars2 = "ABCDEFG".toCharArray();

        new Thread(()->{
            for (char c: char1) {
                while (r != READYTORUN.T1 ){

                }
                System.out.println(c);
                r = READYTORUN.T2;
            }


        },"t1").start();


        new Thread(()->{
            for (char c:chars2) {
                while (r!=READYTORUN.T2){

                }
                System.out.println(c);
                r = READYTORUN.T1;
            }


        },"t2").start();




    }



}
