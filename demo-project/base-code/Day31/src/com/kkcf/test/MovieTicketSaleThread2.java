package com.kkcf.test;

public class MovieTicketSaleThread2 extends Thread {
    public static int ticketCount = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (MovieTicketSaleThread2.class) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (++ticketCount > 1000) break;

                System.out.println(Thread.currentThread().getName() + "正在卖第" + (ticketCount) + "张票");
            }
        }
    }
}
