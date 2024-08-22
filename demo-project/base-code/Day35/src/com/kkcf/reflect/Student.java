package com.kkcf.reflect;

import java.io.IOError;
import java.io.IOException;

public class Student {
    public String gender;
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    protected Student(String name) {
        this.name = name;
    }

    private Student(int age) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void sleep() {
        System.out.println("睡觉");
    }

    private String eat(String somthing) throws IOException, NullPointerException {
        System.out.println("吃饭" + somthing);
        return "奥利给!";
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
