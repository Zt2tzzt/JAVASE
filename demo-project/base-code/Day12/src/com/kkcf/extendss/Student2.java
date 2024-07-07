package com.kkcf.extendss;

public class Student2 extends Person2 {
    private String schoole;

    public Student2() {
        this(null, 0, "北宇治高等学校");
        System.out.println("Student2 constructor");
    }

    public Student2(String name, int age, String schoole) {
        super(name, age);
        this.schoole = schoole;
    }

    public void sayHello() {
        System.out.println("你好，我是" + this.name + ",今年" + this.age + "岁，我在" + this.schoole + "上学");
    }
}
