package com.kkcf.object_riented;

public class Student {
    private int age;
    private char gender;

    public void method() {
        int age = 10;
        System.out.println(age);
        System.out.println(this.age);
    }

    public char getGender() {
        return gender;
    }
}
