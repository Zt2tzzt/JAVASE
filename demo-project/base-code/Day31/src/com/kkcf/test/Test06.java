package com.kkcf.test;

public class Test06 {
    public static void main(String[] args) {
        OddThread t1 = new OddThread("线程1");
        OddThread t2 = new OddThread("线程2");

        t1.start();
        t2.start();
    }
}
