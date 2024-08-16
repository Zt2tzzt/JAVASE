package com.kkcf.runable;

public class Demo02 {
    public static void main(String[] args) {
        MyRun1 mr = new MyRun1();

        Thread t1 = new Thread(mr, "飞机");
        Thread t2 = new Thread(mr, "坦克");

        t1.setPriority(10);
        t2.setPriority(1);

        t1.start();
        t2.start();
    }
}
