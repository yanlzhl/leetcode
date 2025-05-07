package com.yanlz.algorith.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
/**
  * | 特性         | CountDownLatch (倒计时门闩)         | CyclicBarrier (循环屏障)         |
  * |--------------|------------------------------------|----------------------------------|
  * | 主要目的     | 一个或多个线程等待其他N个操作完成   | 一组N个线程互相等待，直到所有线程都到达某个点 |
  * | 计数方式     | 递减计数                           | 递增计数（内部实现，到达指定parties数量） |
  * | 可重用性     | 不可重用 (一次性)                  | 可重用 (循环使用)                |
  * | await()      | 等待计数变为0                      | 等待其他所有参与线程也调用 await() |
  * | countDown()  | 外部线程或完成任务的线程调用       | 无此对应方法 (所有参与线程都调用await()) |
  * | 屏障动作     | 无内置屏障动作                     | 可选的屏障动作 (Runnable)        |
  * | 参与者       | 等待者和执行 countDown() 者角色可以不同 | 所有参与者角色相同 (都调用await()) |
  * | 核心比喻     | 起跑发令枪 / 任务完成信号          | 集合点 / 阶段性同步点            |
  */
// */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int numberOfPlayers = 3;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfPlayers);

        // 屏障动作：当所有玩家都准备好一轮时，由最后一个到达的玩家执行
        Runnable barrierAction = () -> System.out.println("裁判：所有玩家已准备好本轮，游戏开始！");
        CyclicBarrier barrier = new CyclicBarrier(numberOfPlayers, barrierAction);

        for (int round = 1; round <= 2; round++) { // 模拟进行两轮游戏
            System.out.println("\n--- 第 " + round + " 轮 ---");
            for (int i = 0; i < numberOfPlayers; i++) {
                final int playerId = i + 1;
                final int currentRound = round;
                executor.submit(() -> {
                    try {
                        System.out.println("玩家 " + playerId + " (第 " + currentRound + " 轮): 正在准备...");
                        Thread.sleep((long) (Math.random() * 2000 + 1000)); // 模拟准备时间
                        System.out.println("玩家 " + playerId + " (第 " + currentRound + " 轮): 已准备好，等待其他玩家...");

                        barrier.await(); // 等待其他玩家到达屏障

                        // 所有玩家都到达屏障后，会一起执行到这里
                        System.out.println("玩家 " + playerId + " (第 " + currentRound + " 轮): 开始游戏！");
                        Thread.sleep((long) (Math.random() * 1000)); // 模拟游戏进行
                        System.out.println("玩家 " + playerId + " (第 " + currentRound + " 轮): 本轮游戏结束。");

                    } catch (InterruptedException e) {
                        System.out.println("玩家 " + playerId + " (第 " + currentRound + " 轮): 被中断。");
                        Thread.currentThread().interrupt();
                    } catch (BrokenBarrierException e) {
                        System.out.println("玩家 " + playerId + " (第 " + currentRound + " 轮): 屏障已损坏。");
                    }
                });
            }
            // 注意：实际场景中，如果希望主线程等待每一轮的完成，需要更复杂的同步机制
            // 或者让 barrierAction 设置一个 latch 来通知主线程。
            // 这里为了简化，主线程直接开始下一轮的提交。
            // 但由于 CyclicBarrier 的特性，线程会正确地为下一轮重新在 barrier 处同步。
            try {
                // 给足够时间让上一轮的线程输出完毕，方便观察
                // 在实际应用中，可能需要更精确的同步
                if(round < 2) Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}