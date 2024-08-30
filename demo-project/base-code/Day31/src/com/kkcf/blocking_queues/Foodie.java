package com.kkcf.blocking_queues;

import java.util.concurrent.ArrayBlockingQueue;

public class Foodie extends Thread {
    private final ArrayBlockingQueue<String> queue;

    public Foodie(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            // 不断从队列中，取出咖啡
            String food = null;
            try {
                food = queue.take();
                System.out.println("顾客拿到一个" + food);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
