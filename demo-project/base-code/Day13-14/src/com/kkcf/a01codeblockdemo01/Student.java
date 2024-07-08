package com.kkcf.a01codeblockdemo01;

public class Student {
    private String name;
    private int age;

    public Student() {
        foo();
    }

    public Student(String name, int age) {
        foo();
        this.name = name;
        this.age = age;
    }

    public void foo() {
        System.out.println("开始创建学生对象");
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
}
