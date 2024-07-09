package com.kkcf.interfacee2;

public class BasketballPlayer extends Athlete {
    public BasketballPlayer() {
    }

    public BasketballPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void learn() {
        System.out.println("学打篮球");
    }
}
