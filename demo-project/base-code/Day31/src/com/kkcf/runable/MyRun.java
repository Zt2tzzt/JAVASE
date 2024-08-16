package com.kkcf.runable;

public class MyRun implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // 在打印前，先获取到线程对象
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + "Hello Frog");
        }
    }
}
