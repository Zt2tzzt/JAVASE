package com.kkcf.a04polymorphismdemo4;

public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*public void keepPet(Dog d, String something) {
        System.out.println("年龄为" + age + "岁的" + name + "养了一只" + d.getColor() + "颜色的" + d.getAge() + "岁的狗");
        d.eat(something);;
    }

    public void keepPet(Cat c, String something) {
        System.out.println("年龄为" + age + "岁的" + name + "养了一只" + c.getColor() + "颜色的" + c.getAge() + "岁的猫");
        c.eat(something);;
    }*/

    public void keepPet(Animal a, String something) {
        String animal = a instanceof Dog ? "狗"
                : a instanceof Cat ? "猫"
                : "未知";

        System.out.println("年龄为" + age + "岁的" + name + "养了一只" + a.getColor() + "颜色的" + a.getAge() + "岁的" + animal);
        a.eat(something);
    }
}
