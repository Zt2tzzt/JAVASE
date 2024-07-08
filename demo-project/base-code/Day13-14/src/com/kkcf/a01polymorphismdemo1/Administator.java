package com.kkcf.a01polymorphismdemo1;

public class Administator extends Person {
    public Administator() {
    }

    public Administator(String name, int age) {
        super(name, age);
    }

    @Override
    public void show() {
        System.out.println("管理员的信息为：" + getName() + " " + getAge());;
    }
}
