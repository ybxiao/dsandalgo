package com.example.dsandalgo.juc;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {

    public static void main(String[] args) {
        MyContainer2<String> c =new MyContainer2<>();


        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5;  j++) {

                    System.out.println("consumer :"  +  c.get());

                }
            },"c : " + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) {
                    c.put(Thread.currentThread().getName()+""+ j);
                }


            },"p : "+i).start();
        }



    }



    private final LinkedList<T>  lists = new LinkedList<>();
    private final int max = 10;
    private int count  = 0;

    ReentrantLock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();



    public  void put(T t) {
        try {
            lock.lock();
            while(lists.size() == max){
                producer.await();
            }

            lists.add(t);
            ++count;
            consumer.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    public  T get(){
        T t = null;
        try {
            lock.lock();
            while (lists.size() ==0){
                consumer.await();
            }
            t= lists.removeFirst();
            count --;
            producer.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return t ;

    }


}
