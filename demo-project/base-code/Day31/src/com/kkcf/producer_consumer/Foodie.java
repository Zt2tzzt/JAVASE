package com.kkcf.producer_consumer;

public class Foodie extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count > 0) {
                    // 核心逻辑
                    if (Desk.coffeFlag == 0) {
                        // 桌子上没有咖啡，则等待
                        try {
                            Desk.lock.wait(); // 锁对象，与线程绑定
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    } else {
                        // 桌子上有咖啡，则喝
                        System.out.println("顾客正在喝咖啡，还能再喝" + (--Desk.count) + "杯咖啡");

                        // 桌上的咖啡被喝完了，修改桌子的状态
                        Desk.coffeFlag = 0;

                        // 喝完后，唤醒厨师，做咖啡
                        Desk.lock.notifyAll();
                    }
                } else {
                    break;
                }
            }
        }
    }
}
