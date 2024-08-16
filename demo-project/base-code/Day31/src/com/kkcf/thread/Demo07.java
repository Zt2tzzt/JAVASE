package com.kkcf.thread;

public class Demo07 {
    public static void main(String[] args) throws InterruptedException {
        MyThread2 t = new MyThread2();

        t.setName("土豆");

        t.start();

        // 设置插入线程，将 t 线程，放在当前线程（main 线程）前执行。
        t.join();

        // 当前线程 main
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
