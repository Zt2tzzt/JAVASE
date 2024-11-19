package com.kkcf.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test09 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300, 700));

        LotteryCallable c1 = new LotteryCallable(list);
        LotteryCallable c2 = new LotteryCallable(list);

        FutureTask<Integer> ft1 = new FutureTask<>(c1);
        FutureTask<Integer> ft2 = new FutureTask<>(c2);

        Thread t1 = new Thread(ft1, "抽奖箱1");
        Thread t2 = new Thread(ft2, "抽奖箱2");

        t1.start();
        t2.start();

        Integer max1 = ft1.get();
        Integer max2 = ft2.get();

        boolean flag = max1 > max2;
        Integer max = flag ? max1 : max2;
        String maxNo = flag ? "1" : "2";

        System.out.println("在此次抽奖过程中，抽奖箱" + maxNo + "产生最大奖项，该奖金额为 " + max + " 元。");
    }
}
