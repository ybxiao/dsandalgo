package com.example.dsandalgo.juc;

import java.util.concurrent.Semaphore;

/**
 * created on 2020/7/29.
 * time: 11:04
 * 两个线程交替打印X Y
 * 各打印10次
 * @author yibo.xiao
 */


public class T_Semaphore {

    private static int count = 0;

    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        try {
            s2.acquire();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                while (count <=10){
                    s1.acquire();
                    if(count <=10){
                        System.out.println("--第"+count+"次---"+ 'X');
                    }
                    s2.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                while (count <=10){
                    s2.acquire();

                    System.out.println("====第"+count+"次==="+ 'Y');
                    count++;
                    s1.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();





    }




}
