package com.kkcf.blocking_queues;

import java.util.concurrent.ArrayBlockingQueue;

public class Cook extends Thread {
    private final ArrayBlockingQueue<String> queue;

    public Cook(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            // 不断把咖啡，放到阻塞队列中
            try {
                queue.put("咖啡");
                System.out.println("厨师放了一杯咖啡");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
