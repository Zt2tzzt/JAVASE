package com.kkcf.process_control;

import java.util.Scanner;

public class WhileDemo03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入被除数：");
        int dividend = sc.nextInt();

        System.out.println("请输入除数：");
        int divisor = sc.nextInt();

        sc.close();

        int count = 0;

        while (dividend >= divisor) {
            dividend = dividend - divisor;
            count++;
        }

        System.out.println("商：" + count + " 余数：" + dividend);
    }
}
