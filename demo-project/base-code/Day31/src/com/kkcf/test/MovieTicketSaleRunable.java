package com.kkcf.test;

public class MovieTicketSaleRunable implements Runnable {
    int ticketCount = 0;

    @Override
    public void run() {
        while (true) {
            if (goooo()) break;
        }
    }

    private synchronized boolean goooo() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (ticketCount < 100) {
            System.out.println(Thread.currentThread().getName() + "正在卖第" + (++ticketCount) + "张票");
        } else {
            return true;
        }
        return false;
    }
}