package com.kkcf.interfacee3;

public class InterImpl1 implements Inter1, Inter2 {
    @Override
    public void method() {
        System.out.println("我实现了 method 方法");
    }

    @Override
    public void defaultMethod() {
        System.out.println("我实现了 defaultMethod 默认方法");
    }
}
