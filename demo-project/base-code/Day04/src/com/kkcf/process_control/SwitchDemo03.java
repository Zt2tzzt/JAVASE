package com.kkcf.process_control;

import java.util.Scanner;

public class SwitchDemo03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入今天是星期几：");
        int day = sc.nextInt();

        sc.close();

        switch (day) {
            case 1, 2, 3, 4, 5 -> System.out.println("工作日");
            case 6, 7 -> System.out.println("休息日");
            default -> System.out.println("输入有误");
        }
    }
}