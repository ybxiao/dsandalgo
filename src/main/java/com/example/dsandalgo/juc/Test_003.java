package com.example.dsandalgo.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test_003 {

    private List list = Collections.synchronizedList(new ArrayList<>());

    public int size(){
        return list.size();
    }
    public void add(Object o){
        list.add(o);
    }

    public static void main(String[] args) {
        Test_003 c  = new Test_003();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" + i );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        },"t1").start();

        new Thread(()->{
            while (true){
                if (c.size()== 5){
                    break;
                }

            }
            System.out.println("t2 tell ....");
        },"t2").start();


    }


}
