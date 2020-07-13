package com.example.dsandalgo.juc;

import java.sql.SQLOutput;

public class Test_002 {
    public static void main(String[] args){

//        testJoin();
        testYield();
    }

    static void testSleep(){
        new Thread(()->{
            for(int i =0;i<100;i++){
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                }catch (Exception ioe){
                    ioe.printStackTrace();
                }

            }
        }).start();

    }

    static void testYield(){
        new Thread(()->{
            for(int i =0;i<100;i++){
                System.out.println("A"+i);
                if (i%10 == 0 ) Thread.yield();
            }

        }).start();

        new Thread(()->{
            for (int i = 0; i <100 ; i++) {
                System.out.println("-------B" + i);
                if (i%10 == 0 ) Thread.yield();
            }

        }).start();

    }

    static  void  testJoin(){
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(() -> {

//            try {
//                t1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            for (int i = 0; i < 100; i++) {
                System.out.println("B" + i);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        t1.start();
        t2.start();
    }
}
