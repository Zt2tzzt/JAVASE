package com.kkcf.test;

public class Test02 {
    public static void main(String[] args) {
        MovieTicketSaleRunable mtsr = new MovieTicketSaleRunable();

        Thread t1 = new Thread(mtsr, "窗口1");
        Thread t2 = new Thread(mtsr, "窗口2");
        Thread t3 = new Thread(mtsr, "窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
