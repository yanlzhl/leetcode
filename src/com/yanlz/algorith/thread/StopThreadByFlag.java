package com.yanlz.algorith.thread;

/**
 * 总结：
 * 共享变量（标志位）： 适用于线程代码可以规律地检查该标志位的情况(while 一直在检查)，且线程不涉及长时间的、不可中断的阻塞操作。实现简单，但适用性有限。
 * interrupt() 方法： 是 Java 中推荐的、更通用的线程中断机制。它能有效地唤醒因调用可中断阻塞方法而等待的线程，并允许线程通过检查中断状态来优雅地退出。对于不可中断的阻塞，interrupt() 也能设置一个标志，但线程需要其他机制（如关闭资源）来真正解除阻塞。
 */
class StoppableTask implements Runnable {
    // 使用 volatile 保证可见性
    private volatile boolean running = true;
    private int count = 0;

    public void stopTask() {
        System.out.println(Thread.currentThread().getName() + ": 请求停止任务...");
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": 任务开始运行...");
        while (running) { // 检查共享变量
            // 模拟工作
            System.out.println(Thread.currentThread().getName() + ": 正在执行任务，计数: " + count++);
            try {
                // 模拟一些耗时操作，也给其他线程设置 running = false 的机会
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // 如果在 sleep 期间被中断，也应该停止
                System.out.println(Thread.currentThread().getName() + ": 任务在休眠时被中断，即将停止...");
                running = false; // 或者直接 break;
                // Thread.currentThread().interrupt(); // 重新设置中断状态，如果上层代码需要感知
            }
        }
        System.out.println(Thread.currentThread().getName() + ": 任务已停止。最终计数: " + (count -1));
        // 在这里可以执行一些清理工作
    }
}

public class StopThreadByFlag {
    public static void main(String[] args) throws InterruptedException {
        StoppableTask task = new StoppableTask();
        Thread workerThread = new Thread(task, "WorkerThread");

        workerThread.start();

        // 让工作线程运行一段时间
        Thread.sleep(2000);

        // 从主线程请求停止工作线程
        System.out.println(Thread.currentThread().getName() + ": 主线程请求停止 WorkerThread...");
        task.stopTask();

        // 等待工作线程结束
        workerThread.join();
        System.out.println(Thread.currentThread().getName() + ": 主线程确认 WorkerThread 已结束。");
    }
}