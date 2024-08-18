package com.kkcf.test;

import java.util.ArrayList;
import java.util.Arrays;

public class Test08 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700));

        LotteryThread t1 = new LotteryThread(list);
        LotteryThread t2 = new LotteryThread(list);

        t1.setName("抽奖箱1");
        t2.setName("抽奖箱2");

        t1.start();
        t2.start();
    }
}
