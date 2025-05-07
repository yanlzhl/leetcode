package com.yanlz.algorith.thread;

/**
 * 演示了如何使用 join() 方法来等待一个线程完成。
 */
class WorkerThread extends Thread {
    private int sum = 0;
    private int n;

    public WorkerThread(String name, int n) {
        super(name);
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始计算...");
        for (int i = 1; i <= n; i++) {
            sum += i;
            try {
                // 模拟耗时计算
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断了。");
                // 清理或直接返回
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " 计算完成，总和是: " + sum);
    }

    public int getSum() {
        return sum;
    }
}

public class JoinDemo1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " (主线程) 开始执行。");

        WorkerThread worker = new WorkerThread("计算线程-A", 5);
        worker.start(); // 启动工作线程

        System.out.println(Thread.currentThread().getName() + " (主线程) 正在等待 " + worker.getName() + " 完成...");

        try {
            // 主线程调用 worker 线程的 join() 方法
            // 主线程会阻塞在这里，直到 worker 线程执行完毕
            worker.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " (主线程) 在等待时被中断了: " + e.getMessage());
            Thread.currentThread().interrupt(); // 重新设置中断状态
        }

        // 当 worker.join() 返回后，说明 worker 线程已经结束了
        System.out.println(Thread.currentThread().getName() + " (主线程) 检测到 " + worker.getName() + " 已完成。");
        System.out.println(Thread.currentThread().getName() + " (主线程) 获取到的最终计算结果是: " + worker.getSum());
        System.out.println(Thread.currentThread().getName() + " (主线程) 执行完毕。");
    }
}