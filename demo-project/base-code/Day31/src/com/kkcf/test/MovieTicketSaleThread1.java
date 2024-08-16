package com.kkcf.test;

import java.util.concurrent.locks.ReentrantLock;

public class MovieTicketSaleThread1 extends Thread {
    static int ticketCount = 0;
    static final ReentrantLock lock = new ReentrantLock();

    public MovieTicketSaleThread1() {
    }

    public MovieTicketSaleThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            //synchronized (MovieTicketSaleThread.class) {
            lock.lock();
            try {
                Thread.sleep(10);
                if (ticketCount < 100) {
                    System.out.println(Thread.currentThread().getName() + "正在卖第" + (++ticketCount) + "张票");
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            //}
        }

    }
}
