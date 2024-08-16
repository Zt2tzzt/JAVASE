package com.kkcf.thread;

public class Demo02 {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread("飞机");
        MyThread mt2 = new MyThread("坦克");

        mt1.start();
        mt2.start();
    }
}
