package com.kkcf.a03polymorphismdemo3;

public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        //a.lookHome();

        // 解决方案
        if (a instanceof Dog d) {
            d.lookHome();
        } else if (a instanceof Cat c) {
            c.catchMice();
        } else {
            System.out.println("没有这个类型，无法检测");
        }
    }
}

class Animal {
    String name = "动物";

    public void eat() {
        System.out.println("动物吃东西");
    }
}

class Dog extends Animal {
    String name = "狗";

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    public void lookHome() {
        System.out.println("狗看家");
    }
}

class Cat extends Animal {
    String name = "猫";

    @Override
    public void eat() {
        System.out.println("猫吃小鱼干");
    }

    public void catchMice() {
        System.out.println("猫抓老鼠");
    }
}
