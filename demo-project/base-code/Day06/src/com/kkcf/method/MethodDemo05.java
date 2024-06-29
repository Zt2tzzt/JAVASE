package com.kkcf.method;

public class MethodDemo05 {
    public static void main(String[] args) {
        int num = 100;

        System.out.println(num); // 100
        change(num);
        System.out.println(num); // 100
    }

    public static void change(int num) {
        num = 200;
    }
}
