package com.example.dsandalgo.juc;

import java.util.concurrent.locks.LockSupport;

public class T02_001 {

    static Thread t1=null, t2=null;

    public static void main(String[] args) {
        char[] char1 = "1234567".toCharArray();
        char[] chars2 = "ABCDEFG".toCharArray();

        t1=new Thread(()->{
            for (int i = 0; i < char1.length; i++) {
                System.out.println(char1[i]);
                LockSupport.park();
                LockSupport.unpark(t2);

            }


        },"t1");


        t2 = new Thread(()->{
            for (int i = 0; i < chars2.length; i++) {
                System.out.println(chars2[i]);
                LockSupport.unpark(t1);
                LockSupport.park();

            }


        },"t2");


        t1.start();
        t2.start();


    }



}
