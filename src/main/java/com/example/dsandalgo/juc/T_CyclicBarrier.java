package com.example.dsandalgo.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T_CyclicBarrier {


    public static void main(String[] args) {
        CyclicBarrier cb  =  new CyclicBarrier(10,()->{

            System.out.println("ce shi ");

        });

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }

}
