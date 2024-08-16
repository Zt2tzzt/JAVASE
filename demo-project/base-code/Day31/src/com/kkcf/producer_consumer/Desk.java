package com.kkcf.producer_consumer;

public class Desk {
    // 是否有面条：0 表示没有，1 表示有
    public static int foodFlag = 0;

    // 表示顾客对多吃 10 碗面条
    public static int count = 10;

    // 锁对象
    public static final Object lock = new Object();
}
