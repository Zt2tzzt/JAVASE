package com.kkcf.interfacee3;

public interface Inter2 {
    // 接口中的默认方法，默认会加上 public 修饰
    default void defaultMethod() {
        System.out.println("我也是默认方法");
    }
}
