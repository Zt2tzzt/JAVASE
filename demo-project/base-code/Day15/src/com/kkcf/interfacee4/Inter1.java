package com.kkcf.interfacee4;

public interface Inter1 {
    // 接口中的静态方法，默认会加上 public 修饰符
    static void show1() {
        System.out.println("show1 方法开始执行了");
        record();
    }

    // 接口中的静态方法，默认会加上 public 修饰符
    static void show2() {
        System.out.println("show2 方法开始执行了");
        record();
    }

    private static void record() {
        System.out.println("记录程序在运行中的各种细节，此处有 100 行代码");
    }
}
