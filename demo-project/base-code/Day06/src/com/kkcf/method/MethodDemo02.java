package com.kkcf.method;

public class MethodDemo02 {
    public static void main(String[] args) {
        System.out.println('a');
        foo();
        System.out.println('b');
    }

    public static void foo() {
        System.out.println('c');
        System.out.println('d');
    }
}
