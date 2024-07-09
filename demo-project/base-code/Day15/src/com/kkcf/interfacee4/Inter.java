package com.kkcf.interfacee4;

public interface Inter {
    // 接口中的静态方法，会省略修饰符 public
    static void staticMethod() {
        System.out.println("静态方法调用");
    }
}
