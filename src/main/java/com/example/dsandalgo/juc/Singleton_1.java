package com.example.dsandalgo.juc;

public class Singleton_1 {
    private volatile Singleton_1 instance = null;
    private Singleton_1(){}
    final Object o = new Object();
    public Singleton_1 getInstance(){
        if (null == instance){
            synchronized (o){
                if (null == instance){
                    instance = new Singleton_1();
                }

                return instance;
            }


        }
        return instance;



    }

}
