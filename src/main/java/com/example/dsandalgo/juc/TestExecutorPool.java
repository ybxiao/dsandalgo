package com.example.dsandalgo.juc;

import java.util.concurrent.CompletableFuture;

public class TestExecutorPool {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("动作1"));
        completableFuture.thenRun(() -> System.out.println("1"));
    }



}

