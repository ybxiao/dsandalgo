package com.example.dsandalgo.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T_CyclicBarrier_01 {


    public static void main(String[] args) {

        final  int n  =10;
        boolean flag =  false;
        Thread[] soldiers = new Thread[n];
        CyclicBarrier cyclic = new CyclicBarrier(n,new BarrierRun(flag ,n ));
        System.out.println("squad gather...");

        for (int i = 0; i < 10; i++) {
            System.out.println("soldier" + i+  "meet ");
            soldiers[i] = new Thread(new Solidier("士兵" +i, cyclic ));
            soldiers[i].start();

        }

    }

    public static  class Solidier implements Runnable{
        private String soldier;
        private CyclicBarrier cyclicBarrier;

        public Solidier(String soldier,CyclicBarrier cyclicBarrier){
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run(){


            try {

                //jihe
                cyclicBarrier.await();

                //work
                dowork();

                //report

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


        }

        void dowork(){

            try {
                Thread.sleep(Math.abs(new Random().nextInt()%100000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier +"work done");
        }

    }

    public static class BarrierRun implements  Runnable{
        private Boolean flag;
        int n ;

        public BarrierRun(Boolean flag ,int n){
            this.flag = flag;
            this.n = n;

        }

        @Override
        public void run(){
            if (flag){
                System.out.println("soldier "+ n +"worker done");
            }
            else{
                System.out.println("solier "+ n +"meet done ");
                flag = true;
            }

        }
    }

}
