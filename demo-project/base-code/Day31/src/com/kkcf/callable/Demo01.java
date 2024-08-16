package com.kkcf.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mc = new MyCallable();

        FutureTask<Integer> ft = new FutureTask<Integer>(mc);

        Thread t1 = new Thread(ft);

        t1.start();

        // 获取多线程运行的结果
        Integer result = ft.get();
        System.out.println(result);
    }
}
