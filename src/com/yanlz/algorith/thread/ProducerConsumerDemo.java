package com.yanlz.algorith.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 生产者-消费者模式的实现 学习object.wait()和notify()方法
 */
class SharedBuffer {
    private final Queue<Integer> buffer;
    private final int capacity;
    private final Object lock = new Object(); // 用于同步的锁对象

    public SharedBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    // 生产者调用
    public void produce(int item) throws InterruptedException {
        synchronized (lock) {
            // 1. 检查缓冲区是否已满 (必须在循环中检查条件)
            while (buffer.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + ": 缓冲区已满，生产者等待...");
                lock.wait(); // 释放锁并等待
            }

            // 2. 生产数据
            buffer.add(item);
            System.out.println(Thread.currentThread().getName() + ": 生产了 " + item + "，缓冲区大小: " + buffer.size());

            // 3. 通知可能在等待的消费者 (缓冲区不再为空)
            lock.notifyAll(); // 唤醒所有等待的线程 (可能是其他生产者或消费者)
        }
    }

    // 消费者调用
    public int consume() throws InterruptedException {
        synchronized (lock) {
            // 1. 检查缓冲区是否为空 (必须在循环中检查条件)
            while (buffer.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ": 缓冲区为空，消费者等待...");
                lock.wait(); // 释放锁并等待
            }

            // 2. 消费数据
            int item = buffer.poll();
            System.out.println(Thread.currentThread().getName() + ": 消费了 " + item + "，缓冲区大小: " + buffer.size());

            // 3. 通知可能在等待的生产者 (缓冲区不再为满)
            lock.notifyAll(); // 唤醒所有等待的线程 (可能是其他生产者或消费者)
            return item;
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        SharedBuffer sharedBuffer = new SharedBuffer(5); // 缓冲区容量为5

        // 创建生产者线程
        Thread producer1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sharedBuffer.produce(i);
                    Thread.sleep((long) (Math.random() * 100)); // 模拟生产耗时
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "生产者-1");

        Thread producer2 = new Thread(() -> {
            try {
                for (int i = 10; i < 20; i++) {
                    sharedBuffer.produce(i);
                    Thread.sleep((long) (Math.random() * 150));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "生产者-2");


        // 创建消费者线程
        Thread consumer1 = new Thread(() -> {
            try {
                for (int i = 0; i < 8; i++) {
                    sharedBuffer.consume();
                    Thread.sleep((long) (Math.random() * 200)); // 模拟消费耗时
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "消费者-1");

        Thread consumer2 = new Thread(() -> {
            try {
                for (int i = 0; i < 12; i++) { // 消费比生产多，会等待
                    sharedBuffer.consume();
                    Thread.sleep((long) (Math.random() * 250));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "消费者-2");


        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // 注意：这个示例没有优雅地停止线程，实际应用中需要考虑
    }
}