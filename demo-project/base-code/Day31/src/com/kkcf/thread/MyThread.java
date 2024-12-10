package com.kkcf.thread;

public class MyThread extends Thread {
    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("线程" + super.getName() + "被中断，错误信息：" + e.getMessage());
                throw new RuntimeException(e);
            }
            System.out.println(super.getName() + "Hello frog");
        }
    }
}
