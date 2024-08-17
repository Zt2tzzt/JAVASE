package com.kkcf.test;

public class Test4 {
    public static void main(String[] args) {
        MovieTicketSaleThread2 t1 = new MovieTicketSaleThread2();
        MovieTicketSaleThread2 t2 = new MovieTicketSaleThread2();

        t1.setName("窗口1");
        t2.setName("窗口2");

        t1.start();
        t2.start();
    }
}
