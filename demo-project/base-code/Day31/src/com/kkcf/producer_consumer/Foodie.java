package com.kkcf.producer_consumer;

public class Foodie extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count > 0) {
                    // 核心逻辑
                    if (Desk.foodFlag == 0) {
                        // 桌子上没有面条，则等待
                        try {
                            Desk.lock.wait(); // 锁对象，与线程绑定
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    } else {
                        // 桌子上有面条，则吃
                        System.out.println("顾客正在吃面条，还嫩再吃" + (--Desk.count) + "碗面条");

                        // 桌上的面条被吃完了，修改桌子的状态
                        Desk.foodFlag = 0;

                        // 吃完后，唤醒厨师，做面条
                        Desk.lock.notifyAll();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
