package com.kkcf.test;

public class OddThread extends Thread {
    public static int num = 100;

    public OddThread() {
    }

    public OddThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (OddThread.class) {
                if (--num < 0) break;

                if (num % 2 == 1)
                    System.out.println(Thread.currentThread().getName() + "获取到奇数：" + num);
            }
        }
    }
}
