package com.kkcf.process_control;

import java.util.Scanner;

public class IfTest02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入电影票号：");
        int ticket = sc.nextInt();

        sc.close();

        if (ticket >= 0 && ticket <= 100) {
            if (ticket % 2 == 0)
                System.out.println("坐右边");
            else
                System.out.println("坐左边");
        }

    }
}
