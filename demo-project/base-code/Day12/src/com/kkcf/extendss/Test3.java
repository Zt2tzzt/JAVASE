package com.kkcf.extendss;

public class Test3 {
    public static void main(String[] args) {
        OverseasStudent s = new OverseasStudent();

        s.lunch();
    }

}

class Person {
    public void eat() {
        System.out.println("吃米饭，吃菜");
    }

    public void drink() {
        System.out.println("喝开水");
    }
}

class Student extends Person {
    public void lunch() {
        eat();
        drink();

        this.eat();
        this.drink();

        super.eat();
        super.drink();
    }
}

class OverseasStudent extends Person {
    @Override
    public void eat() {
        System.out.println("吃意大利面");
    }

    @Override
    public void drink() {
        System.out.println("喝凉水 ");
    }

    public void lunch() {
        eat(); // 吃意大利面
        drink(); // 喝凉水

        super.eat(); // 吃米饭，吃菜
        super.drink(); // 喝开水
    }
}
