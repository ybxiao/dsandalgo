package com.example.dsandalgo.juc;

import java.util.concurrent.*;

public class TestExecutorPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Thread t1 = null;
        try {
            t1 = new Thread(() -> {
                System.out.println("thread name:" + Thread.currentThread().getName());
                int i = 1 / 0;
                System.out.println("thread name:" + Thread.currentThread().getName() +"end");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.submit(t1);
        //executorService.execute(t1);

        Thread.sleep(1);
       /* boolean b = executorService.awaitTermination(5, TimeUnit.MILLISECONDS);
        executorService.shutdown();*/

        executorService.shutdown();


    }
}
