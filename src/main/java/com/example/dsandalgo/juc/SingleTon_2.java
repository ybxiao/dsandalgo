package com.example.dsandalgo.juc;

import java.util.HashSet;
import java.util.Iterator;

public class SingleTon_2 {
    private SingleTon_2 instance = new SingleTon_2();

    private SingleTon_2(){}

    public SingleTon_2 getInstance(){
        if (null == instance){
            synchronized (this){
                if (null == instance){
                    instance = new SingleTon_2();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        /*new Thread(()->{
            System.out.println(111);

        },"t2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        },"t1").start();*/

        class  T1 extends Thread{





        }
        new T1().start();



    }

    public void test(){
        HashSet ts = new HashSet();
        Iterator iterator = ts.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
