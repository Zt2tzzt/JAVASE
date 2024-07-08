package com.kkcf.a01codeblockdemo01;

public class Teacher {
    private String name;
    private int age;

    static {
        System.out.println("静态代码块执行了");
    }

    public Teacher() {
    }

    public Teacher(String name, int age) {
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
}
