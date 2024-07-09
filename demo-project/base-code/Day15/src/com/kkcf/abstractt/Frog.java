package com.kkcf.abstractt;

public class Frog extends Animal {
    public Frog() {
    }

    public Frog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Frog{} " + super.toString();
    }

    @Override
    public void eat() {
        System.out.println("青蛙在吃虫子");
    }
}
