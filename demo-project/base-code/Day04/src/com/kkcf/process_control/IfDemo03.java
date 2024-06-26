package com.kkcf.process_control;

import java.util.Scanner;

public class IfDemo03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你带的钱：");
        int money = sc.nextInt();

        sc.close();

        if (money > 100) {
            System.out.println("去网红餐厅");
        } else {
            System.out.println("去经济实惠的沙县小吃");
        }
    }
}
