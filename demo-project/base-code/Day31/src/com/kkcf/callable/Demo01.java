package com.kkcf.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();

        FutureTask<Integer> ft1 = new FutureTask<>(mc);
        FutureTask<Integer> ft2 = new FutureTask<>(mc);

        Thread t1 = new Thread(ft1);
        Thread t2 = new Thread(ft2);

        t1.start();
        t2.start();

        // 获取多线程运行的结果
        Integer result1 = ft1.get();
        Integer result2 = ft2.get();
        System.out.println(result1);
        System.out.println(result2);
    }
}
