package com.example.dsandalgo.juc;

import java.util.ArrayList;
import java.util.List;

public class T_004 {

    volatile List  lists = new ArrayList();

    public int size(){
        return lists.size();
    }
    public void add(Object o){
        lists.add(o);
    }


    public static void main(String[] args) throws InterruptedException {
        T_004 c = new T_004();

        final Object lock = new Object();

        new Thread(()->{
            System.out.println("t2 qidong  ");
            synchronized (lock){
                if (c.size() != 5){

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.println("t2 tongzhi ");
                lock.notify();
            }

        },"t2").start();

        Thread.sleep(1000);

        new Thread(()->{
            System.out.println("t1 qidong" );
            synchronized (lock){
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size()  == 5){
                        try {
                            lock.notify();
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"t1").start();

    }


}
