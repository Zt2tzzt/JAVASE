package com.kkcf.logic_operator;

public class LogicOperatorTest01 {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;

        boolean result = ++a < 5 & ++b < 5;
        System.out.println(result); // false

        System.out.println(a); // 11
        System.out.println(b); // 11
    }
}
