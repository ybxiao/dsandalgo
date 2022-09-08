package com.example.dsandalgo.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class T_SafePoint {

    public static AtomicInteger num  = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () ->{
            for (int j = 0; j < 100000000; j++) {
                num.getAndAdd(1);
                if (j % 1000 == 1){
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            System.out.println(Thread.currentThread().getName() + "执行结束");
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num:" + num);

    }


}
