package com.kkcf.threadpoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        MyRunnable r1 = new MyRunnable();
        MyRunnable r2 = new MyRunnable();
        MyRunnable r3 = new MyRunnable();
        MyRunnable r4 = new MyRunnable();
        MyRunnable r5 = new MyRunnable();

        pool.submit(r1);
        Thread.sleep(1000);
        pool.submit(r2);
        Thread.sleep(1000);
        pool.submit(r3);
        Thread.sleep(1000);
        pool.submit(r4);
        Thread.sleep(1000);
        pool.submit(r5);

        pool.shutdown();
    }
}
