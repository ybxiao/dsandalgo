package com.example.dsandalgo;


import java.util.concurrent.*;

public class Test {

    public static void testExecutor() throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(1, 4, 4, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            try {
                executorService.submit(() -> {
                    System.out.println("tasnNo:" + finalI + "__"+Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e) {
                System.out.println("RejectedExecutionException" + i);
            }
            Thread.sleep(5);

        }



        executorService.awaitTermination(1000, TimeUnit.MICROSECONDS);

    }





    public static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }

    public static void main(String[] args) {
        try {
            testExecutor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
