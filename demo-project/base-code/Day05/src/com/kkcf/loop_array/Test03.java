package com.kkcf.loop_array;

import java.util.Random;
import java.util.Scanner;

public class Test03 {
    public static void main(String[] args) {
        // 生成随机数
        Random r = new Random();
        int num = r.nextInt(100) + 1; // 1-100

        // 录入数字
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你猜的数字：");
        int guessNum = sc.nextInt();

        while (true) {
            if (guessNum > num) {
                System.out.println("猜大了");
            } else if (guessNum < num) {
                System.out.println("猜小了");
            } else {
                System.out.println("猜对了，数字是 " + num);
                break;
            }

            System.out.println("请输入你猜的数字：");
            guessNum = sc.nextInt();
        }

        sc.close();

    }
}
