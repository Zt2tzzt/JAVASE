package com.kkcf.thread;

public class Demo06 {
    public static void main(String[] args) {
        MyThread3 t1 = new MyThread3();
        MyThread3 t2 = new MyThread3();

        t1.setName("飞机");
        t2.setName("坦克");

        t1.start();
        t2.start();

    }
}
