package com.kkcf.test;

public class DeadThread extends Thread{
    static final Object obja = new Object();
    static final Object objb = new Object();
    @Override
    public void run() {
        while (true) {
            if ("线程A".equals(this.getName())) {
                synchronized (obja) {
                    System.out.println("线程A拿到了A锁，准备拿B锁");
                    synchronized (objb) {
                        System.out.println("线程A拿到了B锁，顺利执行完一轮");
                    }
                }
            } else {
                synchronized (objb) {
                    System.out.println("线程B拿到了B锁，准备拿A锁");
                    synchronized (obja) {
                        System.out.println("线程B拿到了A锁，顺利执行完一轮");
                    }
                }
            }
        }
    }
}
