package com.kkcf.producer_consumer;

public class Cook extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count > 0) {
                    // 核心逻辑
                    if (Desk.foodFlag == 1) {
                        // 桌子上有面条，则等待
                        try {
                            Desk.lock.wait(); // 锁对象，与线程绑定
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 桌子上没有面条，则做面条；
                        System.out.println("厨师做了一碗面条");

                        // 将面条放在桌上，修改桌子的状态
                        Desk.foodFlag = 1;

                        // 面条做完，唤醒顾客，吃面条
                        Desk.lock.notifyAll();
                    }

                } else {
                    break;
                }
            }
        }

    }
}
