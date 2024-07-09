package com.kkcf.interfacee3;

public interface Inter1 {
    // 接口中的方法，默认会加上 public abstract 修饰
    void method();

    // 接口中的默认方法，默认会加上 public 修饰
    default void defaultMethod() {
        System.out.println("我是默认方法");
    }
}
