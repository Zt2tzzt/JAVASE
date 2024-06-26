package com.kkcf.process_control;

import java.util.Scanner;

public class IfDemo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入女婿的酒量：");
        int wine = sc.nextInt();

        sc.close();

        if (wine > 2) {
            System.out.println("小伙子，不错哦！");
        }
    }
}
