package com.kkcf.loop_array;

import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个整数：");
        int x = sc.nextInt();

        sc.close();

        boolean isPrime = true; // 是否是一个质数

        for (int i = 2; i < Math.sqrt(x); i++) {
            if (x % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime)
            System.out.println(x + " 是质数");
        else
            System.out.println(x + " 不是质数");
    }
}
