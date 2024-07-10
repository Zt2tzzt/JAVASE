package com.kkcf.innerclass;

public class Test3 {
    public static void main(String[] args) {
        Outer3.Inner3 oi = new Outer3.Inner3();

        oi.show1();

        Outer3.Inner3.show2();
    }
}
