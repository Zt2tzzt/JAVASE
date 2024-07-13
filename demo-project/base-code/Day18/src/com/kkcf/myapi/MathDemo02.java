package com.kkcf.myapi;

public class MathDemo02 {
    public static void main(String[] args) {
        int num = 9;
        System.out.println(num + (isPrime(num) ? " 是质数" : " 不是质数"));
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}
