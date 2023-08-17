package com.example.dsandalgo.algo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class T_TestCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(T_TestCompletableFuture::sleepM);

        CompletableFuture<Void> voidCompletableFuture1 = voidCompletableFuture.thenRun(() -> System.out.println("step 1"));
        voidCompletableFuture1.thenRun(() -> System.out.println("step 1.1"));
        voidCompletableFuture1.thenRun(() -> System.out.println("step 1.2"));
        voidCompletableFuture1.thenRun(() -> System.out.println("step 1.3"));

        voidCompletableFuture.thenRun(() -> System.out.println("step 2"));
        voidCompletableFuture.thenRun(() -> System.out.println("step 3"));

        voidCompletableFuture.get();


    }

    public static void sleepM() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("step start");
    }
}
