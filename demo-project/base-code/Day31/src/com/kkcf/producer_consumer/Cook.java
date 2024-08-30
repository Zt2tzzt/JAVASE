package com.kkcf.producer_consumer;

public class Cook extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count > 0) {
                    // 核心逻辑
                    if (Desk.coffeFlag == 1) {
                        // 桌子上有咖啡，则等待
                        try {
                            Desk.lock.wait(); // 锁对象，与线程绑定
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // 桌子上没有咖啡，则做咖啡；
                        System.out.println("厨师做了一杯咖啡");

                        // 将咖啡放在桌上，修改桌子的状态
                        Desk.coffeFlag = 1;

                        // 咖啡做完，唤醒顾客，喝咖啡
                        Desk.lock.notifyAll();
                    }

                } else {
                    break;
                }
            }
        }

    }
}
