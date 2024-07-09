package com.kkcf.innerclass;

public class Outer2 {
    private int a = 10;

    class Inner2 {
        private int a = 20;

        public void show() {
            int a = 30;

            System.out.println(a); // 30
            System.out.println(this.a); // 29
            System.out.println(Outer2.this.a); // 10
        }

    }
}
