package com.yanlz.algorith.thread;

/**
 * 演示了如何使用 join(long millis) 方法来等待一个线程完成，最多等待指定的时间。
 */
class LongRunningTask extends Thread {
    public LongRunningTask(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始执行一个耗时任务...");
        try {
            // 模拟一个需要5秒才能完成的任务
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 被中断了。");
            return;
        }
        System.out.println(Thread.currentThread().getName() + " 耗时任务执行完毕。");
    }
}

public class JoinDemo2 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " (主线程) 开始执行。");

        LongRunningTask task = new LongRunningTask("耗时任务线程");
        task.start();

        System.out.println(Thread.currentThread().getName() + " (主线程) 尝试等待 " + task.getName() + " 最多 2 秒...");

        try {
            // 主线程只等待 task 线程最多 2000 毫秒 (2秒)
            task.join(2000);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " (主线程) 在等待时被中断了: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        if (task.isAlive()) {
            System.out.println(Thread.currentThread().getName() + " (主线程) 等待超时! " + task.getName() + " 仍在运行。");
            System.out.println(Thread.currentThread().getName() + " (主线程) 不再等待，继续执行自己的任务...");
            // 可以选择中断任务线程，如果需要
            // System.out.println(Thread.currentThread().getName() + " (主线程) 尝试中断 " + task.getName());
            // task.interrupt();
        } else {
            System.out.println(Thread.currentThread().getName() + " (主线程) " + task.getName() + " 在超时前已完成。");
        }

        System.out.println(Thread.currentThread().getName() + " (主线程) 执行完毕。");
    }
}