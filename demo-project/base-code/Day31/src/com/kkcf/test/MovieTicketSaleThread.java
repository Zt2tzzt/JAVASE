package com.kkcf.test;

public class MovieTicketSaleThread extends Thread {
    static int ticketCount = 0;

    // 锁对象
    // static final Object lock = new Object();

    public MovieTicketSaleThread() {
    }

    public MovieTicketSaleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (MovieTicketSaleThread.class) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                if (ticketCount < 100)
                    System.out.println(Thread.currentThread().getName() + "正在卖第" + (++ticketCount) + "张票");
                else
                    break;
            }
        }
    }
}
