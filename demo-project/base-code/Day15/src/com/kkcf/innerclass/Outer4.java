package com.kkcf.innerclass;

public class Outer4 {
    int b = 20;

    public void show() {
        int a = 10;

        class Inner4 {
            String name;
            int age;

            public void method1() {
                System.out.println(b); // 20
                System.out.println(a); // 10

                System.out.println("局部内部类中的 method1 方法");
            }

            public static void method2() {
                System.out.println("局部内部类中的 method2 静态方法");
            }
        }

        Inner4 i = new Inner4();
        System.out.println(i.name);
        System.out.println(i.age);
        i.method1();
        Inner4.method2();
    }
}
