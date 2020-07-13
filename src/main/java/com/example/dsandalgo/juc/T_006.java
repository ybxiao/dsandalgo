package com.example.dsandalgo.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class T_006 {

    volatile List  lists = new ArrayList();

    public int size(){
        return lists.size();
    }
    public void add(Object o){
        lists.add(o);
    }


    public static void main(String[] args) throws InterruptedException {
        T_006 c = new T_006();



        Thread t2 =new Thread(()->{
            System.out.println("t2 qidong  ");

            LockSupport.park();

            System.out.println("t2 通知");

        },"t2");



        Thread t1 = new Thread(()->{
            System.out.println("t1 qidong" );

                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size()  == 5){
                       LockSupport.unpark(t2);
                    }
                }

        },"t1");

        t2.start();
        t1.start();

    }


}
