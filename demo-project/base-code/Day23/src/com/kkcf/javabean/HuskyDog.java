package com.kkcf.javabean;

public class HuskyDog extends Dog {
    public HuskyDog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println("一只叫做 " + getName() + " 的 " + getAge() + " 岁的泰迪，正在吃骨头，边吃边拆家。");
    }
}
