package com.kkcf.producer_consumer;

public class Desk {
    // 是否有咖啡：0 表示没有，1 表示有
    public static int coffeFlag = 0;

    // 表示顾客最多喝 10 杯咖啡
    public static int count = 10;

    // 锁对象
    public static final Object lock = new Object();
}
