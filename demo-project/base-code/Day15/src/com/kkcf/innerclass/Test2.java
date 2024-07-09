package com.kkcf.innerclass;

public class Test2 {
    public static void main(String[] args) {
        Outer2.Inner2 oi = new Outer2().new Inner2();

        oi.show();
    }
}
