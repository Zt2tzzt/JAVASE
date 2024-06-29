package com.kkcf.trainning;

public class Test02 {
    public static void main(String[] args) {
        int count = 0;

        for (int i = 101; i <= 200; i++) {
            boolean isPrime = true;

            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(i + " 是素数");
                count++;
            }
        }

        System.out.println("共有 " + count + " 个质数");
    }
}
