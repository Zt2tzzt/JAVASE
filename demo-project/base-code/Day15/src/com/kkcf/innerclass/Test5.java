package com.kkcf.innerclass;

public class Test5 {
    public static void main(String[] args) {
        method(new Animal() {
            @Override
            public void eat() {
                System.out.println("狗在吃骨头");
            }
        });

        Swim s = new Swim() {
            @Override
            public void swim() {
                System.out.println("重写游泳的方法");
            }
        };
        s.swim();

        new Swim() {
            @Override
            public void swim() {
                System.out.println("重写游泳的方法");
            }
        }.swim();
    }

    public static void method(Animal a) {
        a.eat();
    }
}
