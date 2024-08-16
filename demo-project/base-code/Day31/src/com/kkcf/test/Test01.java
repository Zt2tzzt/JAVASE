package com.kkcf.test;

public class Test01 {
    public static void main(String[] args) {
        MovieTicketSaleThread1 t1 = new MovieTicketSaleThread1("窗口1");
        MovieTicketSaleThread1 t2 = new MovieTicketSaleThread1("窗口2");
        MovieTicketSaleThread1 t3 = new MovieTicketSaleThread1("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
