package com.example.dsandalgo.stream;

import java.util.concurrent.CountDownLatch;

public class T_002 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 888; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    String parter = "【" + Thread.currentThread().getName() + "】";
                    System.out.println(parter + "开始执行……");
                    /*GiftTicket giftTicket = new GiftTicket();
                    saveGiftTicket(giftTicket);*/
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(2000);
        // 抢礼品
        countDownLatch.countDown();
    }
}
