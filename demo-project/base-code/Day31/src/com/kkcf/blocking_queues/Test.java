package com.kkcf.blocking_queues;

import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);  // 有界的阻塞队列，必须指定长度

        Cook c = new Cook(queue);
        Foodie f = new Foodie(queue);

        c.start();
        f.start();
    }
}
