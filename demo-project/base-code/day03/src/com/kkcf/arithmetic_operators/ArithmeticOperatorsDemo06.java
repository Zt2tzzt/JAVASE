package com.kkcf.arithmetic_operators;

public class ArithmeticOperatorsDemo06 {
    public static void main(String[] args) {
        int x = 10;
        int y = x++;
        int z = ++x;

        System.out.println("x = " + x); // x = 12
        System.out.println("y = " + y); // y = 10
        System.out.println("z = " + z); // z = 12
    }
}
