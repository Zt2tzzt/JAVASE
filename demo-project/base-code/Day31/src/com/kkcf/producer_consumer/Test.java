package com.kkcf.producer_consumer;

public class Test {
    public static void main(String[] args) {
        Foodie foodie = new Foodie();
        Cook cook = new Cook();

        foodie.setName("顾客");
        cook.setName("厨师");

        foodie.start();
        cook.start();
    }
}
