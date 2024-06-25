package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo07 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        a += b; // 等同于 a = (int)(a + b)

        System.out.println(a); // 30
        System.out.println(b); // 20

        short s = 1;

        s += 1; // 等同于 s = (short)(s + 1)

        System.out.println(s); // 2
    }
}
