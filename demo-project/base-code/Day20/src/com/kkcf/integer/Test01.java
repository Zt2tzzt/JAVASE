package com.kkcf.integer;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个浮点数");
        String s = sc.nextLine();
        double v = Double.parseDouble(s);

        System.out.println(v + 1);
    }
}
