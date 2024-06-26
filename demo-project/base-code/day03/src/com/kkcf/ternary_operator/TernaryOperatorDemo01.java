package com.kkcf.ternary_operator;

public class TernaryOperatorDemo01 {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println(a > b ? a : b); // 20

        int max = a > b ? a : b;
        System.out.println(max); // 20
    }
}
