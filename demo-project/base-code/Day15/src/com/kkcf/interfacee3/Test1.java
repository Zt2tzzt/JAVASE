package com.kkcf.interfacee3;

public class Test1 {
    public static void main(String[] args) {
        InterImpl1 ii = new InterImpl1();

        ii.method(); // 我实现了 method 方法
        ii.defaultMethod(); // 我实现了 defaultMethod 默认方法
    }
}
