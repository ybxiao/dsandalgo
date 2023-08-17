package com.example.dsandalgo.juc;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class TestExecutorPool {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.println("动作1"));
        completableFuture.thenRun(() -> System.out.println("1"));
    }



}

