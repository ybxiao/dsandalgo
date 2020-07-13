package com.example.dsandalgo.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T_005 {

    volatile List  lists = new ArrayList();

    public int size(){
        return lists.size();
    }
    public void add(Object o){
        lists.add(o);
    }


    public static void main(String[] args) throws InterruptedException {
        T_005 c = new T_005();

        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        new Thread(()->{
            System.out.println("t2 qidong  ");
            while (c.size() != 5){
                try {
                    latch1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 通知");
            latch2.countDown();
        },"t2").start();



        new Thread(()->{
            System.out.println("t1 qidong" );

                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size()  == 5){
                        latch1.countDown();
                        try {
                            latch2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        },"t1").start();

    }


}
