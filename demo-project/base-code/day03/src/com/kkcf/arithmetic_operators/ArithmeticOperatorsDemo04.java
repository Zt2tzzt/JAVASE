package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo04 {
    public static void main(String[] args) {
        System.out.println(1 + 2 + "abc" + 2 + 1); // 3abc21

        System.out.println(3.7 + "abc"); // 3.7abc

        System.out.println("abc" + true); // abctrue

        System.out.println('中' + "abc" + true); // 中abctrue
    }
}
