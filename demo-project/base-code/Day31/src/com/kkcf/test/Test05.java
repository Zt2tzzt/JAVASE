package com.kkcf.test;

public class Test05 {
    public static void main(String[] args) {
        SendGiftsThread t1 = new SendGiftsThread("同学A");
        SendGiftsThread t2 = new SendGiftsThread("同学B");

        t1.start();
        t2.start();
    }
}
