package com.kkcf.method;

public class MethodTest01 {
    public static void main(String[] args) {
        getCirleArea(5);
    }

    public static void getCirleArea(double radius) {
        double result = Math.PI * radius * radius;

        System.out.println(result);
    }
}
