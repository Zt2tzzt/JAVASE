package com.kkcf.process_control;

import java.util.Scanner;

public class ForDemo04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请录入第一个数字，表示范围开始：");
        int start = sc.nextInt();

        System.out.println("请录入第二个数字，表示范围结束：");
        int end = sc.nextInt();

        sc.close();

        int count = 0;

        for (int i = start; i <= end; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
