package com.example.dsandalgo.juc;

import java.util.LinkedList;

public class MyContainer1<T> {

    public static void main(String[] args) {
        MyContainer1<String> c =new MyContainer1<>();


        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5;  j++) {
                    System.out.println(c.get());

                }
            },"c" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) {
                    try {
                        c.put(Thread.currentThread().getName()+""+ j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            },"p"+i).start();
        }



    }



    private final LinkedList<T>  lists = new LinkedList<>();
    private final int max = 10;
    private int count  = 0;


    public synchronized  void put(T t) throws InterruptedException {
        while(lists.size() == max){
            this.wait();
        }

        lists.add(t);
        ++count;
        this.notifyAll();
    }

    public synchronized T get(){
        T t = null;
        while (lists.size() ==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t= lists.removeFirst();
        count --;
        this.notifyAll();
        return t ;

    }


}
