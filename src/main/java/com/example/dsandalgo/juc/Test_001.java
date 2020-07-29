package com.example.dsandalgo.juc;

public class Test_001 {
    public static void main(String[] args){

//        Executors.newCachedThreadPool()
        
        int n = 3;


        System.out.println(1212);
    }
//    创建线程的几种方式
    public void m1(){
        new Thread(()->{
            for(int i =0;i<10;i++){

            }

        });
    }

    class MyThread extends Thread{
        @Override
        public void run(){


        }

    }
    class MyRun implements Runnable{
        @Override
        public void run(){


        }
    }
}
