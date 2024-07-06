package com.kkcf.staticc;

public class Student2 {
    String name;
    int age;
    static String teacher;

    // public void show() { // 等同于 👇
    public void show(Student2 this) {
        System.out.println(name + " " + age + " " + teacher);;

        // show2() // 等同于
        this.show2();
    }


    public void show2(Student2 this) {
        System.out.println(name + " " + age + " " + teacher);;
    }

    // 直接报错 👇
    /*public static void sayHello(Student2 this) {
        System.out.println(this.name = "say hello!");
    }*/
}