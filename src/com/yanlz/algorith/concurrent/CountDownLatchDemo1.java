package com.yanlz.algorith.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        int numberOfWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numberOfWorkers); // 初始化计数为3

        ExecutorService executor = Executors.newFixedThreadPool(numberOfWorkers);

        System.out.println("主线程：分配任务给工作线程...");

        for (int i = 0; i < numberOfWorkers; i++) {
            final int workerId = i + 1;
            executor.submit(() -> {
                try {
                    System.out.println("工作线程 " + workerId + " 开始工作...");
                    Thread.sleep((long) (Math.random() * 2000 + 1000)); // 模拟工作
                    System.out.println("工作线程 " + workerId + " 完成工作。");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown(); // 工作完成，计数减一
                    System.out.println("工作线程 " + workerId + " 调用 countDown(), 当前 latch 计数: " + latch.getCount());
                }
            });
        }

        System.out.println("主线程：等待所有工作线程完成...");
        latch.await(); // 主线程阻塞，直到 latch 计数为0

        System.out.println("主线程：所有工作线程已完成，继续执行后续任务。");
        executor.shutdown();
    }
}