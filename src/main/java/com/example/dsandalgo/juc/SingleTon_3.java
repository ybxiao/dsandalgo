package com.example.dsandalgo.juc;

public class SingleTon_3 {

    public static volatile  SingleTon_3 instance ;
    private SingleTon_3(){}
    private static Object lock;
    public static SingleTon_3 getInstance(){

        if (instance == null){
            synchronized (lock){
                return instance = new SingleTon_3();
            }

        }
        return instance;

    }
}
