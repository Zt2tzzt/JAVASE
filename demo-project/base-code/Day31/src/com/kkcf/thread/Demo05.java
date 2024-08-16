package com.kkcf.thread;

public class Demo05 {
    public static void main(String[] args) {
        MyThread1 t1 = new MyThread1();
        MyThread2 t2 = new MyThread2();

        t1.setName("wee");
        t2.setName("zzt");

        t2.setDaemon(true);

        t1.start();
        t2.start();
    }
}
