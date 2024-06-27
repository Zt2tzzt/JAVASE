package com.kkcf.loop_array;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个整数：");
        int x = sc.nextInt();

        sc.close();

        for (int i = 1; i < x; i++) {
            if (i * i > x) {
                System.out.println(x + " 的平方根是 " + (i - 1));
                break;
            } else if (i * i == x) {
                System.out.println(x + " 的平方根是 " + i);
                break;
            }
        }
    }
}
