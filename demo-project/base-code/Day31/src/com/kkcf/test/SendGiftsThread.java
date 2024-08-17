package com.kkcf.test;

public class SendGiftsThread extends Thread {
    public static int giftCount = 100;

    public SendGiftsThread() {
    }

    public SendGiftsThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (SendGiftsThread.class) {

                if (--giftCount < 9) break;

                System.out.println(Thread.currentThread().getName() + "送出了一份礼物，还剩" + giftCount + "个礼物");
            }
        }
    }
}
