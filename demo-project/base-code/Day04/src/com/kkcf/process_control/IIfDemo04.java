package com.kkcf.process_control;

import java.util.Scanner;

public class IIfDemo04 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入小明的成绩");
        int score = sc.nextInt();

        sc.close();

        if (score >= 0 && score <= 100) {
            if (score >= 95 && score <= 100) {
                System.out.println("送自行车一辆");
            } else if (score >= 90 && score <= 94) {
                System.out.println("游乐场玩一天");
            } else if (score >= 80 && score <= 89) {
                System.out.println("变形金刚一个");
            } else {
                System.out.println("胖揍一顿");
            }
        } else {
            System.out.println("分数不合法");
        }
    }
}
