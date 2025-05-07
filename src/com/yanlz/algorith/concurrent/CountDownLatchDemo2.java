package com.yanlz.algorith.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        int numberOfRunners = 5;
        CountDownLatch startSignal = new CountDownLatch(1); // 信号门闩，初始计数为1
        ExecutorService executor = Executors.newFixedThreadPool(numberOfRunners);

        System.out.println("裁判：准备发令枪...");

        for (int i = 0; i < numberOfRunners; i++) {
            final int runnerId = i + 1;
            executor.submit(() -> {
                try {
                    System.out.println("运动员 " + runnerId + " 已就位，等待发令枪...");
                    startSignal.await(); // 等待信号
                    System.out.println("运动员 " + runnerId + " 开始跑！");
                    // 模拟跑步
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println("运动员 " + runnerId + " 到达终点。");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        Thread.sleep(2000); // 裁判做一些准备工作
        System.out.println("裁判：各就各位，预备...砰！");
        startSignal.countDown(); // 发出开始信号，计数减为0，所有运动员开始跑

        executor.shutdown();
        // 注意：这里主线程不等运动员跑完就可能退出了，如果需要等待，可以再用一个latch
    }
}