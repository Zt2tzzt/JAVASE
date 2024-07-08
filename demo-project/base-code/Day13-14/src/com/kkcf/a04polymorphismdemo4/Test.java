package com.kkcf.a04polymorphismdemo4;

public class Test {
    public static void main(String[] args) {
        Person p1 = new Person("老王", 35);
        p1.keepPet(new Dog(2, "黑"), "骨头");

        Person p2 = new Person("老李", 25);
        p2.keepPet(new Cat(3, "灰"), "小鱼干");
    }
}
