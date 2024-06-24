package com.kkcf.arithmetic_operators_test;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        // 键盘输入
        System.out.println("请输入一个三位数：");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();

        // 获取数值的个、十、百位
        int ge = num % 10;
        int shi = num / 10 % 10;
        int bai = num / 100 % 10;

        System.out.println("个位：" + ge + " 十位：" + shi + " 百位：" + bai);

    }
}
