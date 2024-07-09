package com.kkcf.interfacee;

public class Rabbit extends Animal {
    public Rabbit() {
    }

    public Rabbit(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Rabbit{} " + super.toString();
    }

    @Override
    public void eat() {
        System.out.println("兔子在吃胡萝卜");
    }
}
