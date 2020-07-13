package com.example.dsandalgo.juc;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {

    public static void main(String[] args) {
        MyContainer2<String> c =new MyContainer2<>();


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

    ReentrantLock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();



    public   void put(T t) throws InterruptedException {
        lock.lock();
        while(lists.size() == max){
            producer.await();
        }

        lists.add(t);
        ++count;
        consumer.signalAll();
        lock.unlock();
    }

    public  T get(){
        lock.lock();
        T t = null;
        while (lists.size() ==0){
            try {
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t= lists.removeFirst();
        count --;
        producer.signalAll();
        lock.unlock();
        return t ;
    }


}
