package com.example.dsandalgo.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

public class T_007 {

    volatile List  lists = new ArrayList();

    public int size(){
        return lists.size();
    }
    public void add(Object o){
        lists.add(o);
    }


    public static void main(String[] args) throws InterruptedException {
        T_007 c = new T_007();

        Semaphore s  = new Semaphore(1);

        Thread t2 =new Thread(()->{
            System.out.println("t2 qidong  ");

            while (c.size() !=5) {
                try {
                    s.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            System.out.println("t2 通知");


        },"t2");



        Thread t1 = new Thread(()->{
            System.out.println("t1 qidong" );


            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size()  == 5){
                        s.release();
                    }
                }

        },"t1");


        t1.start();
        t2.start();

    }


}
