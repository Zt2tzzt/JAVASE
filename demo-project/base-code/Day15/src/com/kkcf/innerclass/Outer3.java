package com.kkcf.innerclass;

public class Outer3 {
    int a = 10;
    static int b = 20;

    static class Inner3 {
        public void show1() {
            System.out.println("非静态方法调用了");

            Outer3 o = new Outer3();
            System.out.println(o.a); // 10
            System.out.println(b); // 20
        }

        public static void show2() {
            System.out.println("静态方法调用了");

            Outer3 o = new Outer3();
            System.out.println(o.a); // 10
            System.out.println(b); // 20
        }
    }
}
