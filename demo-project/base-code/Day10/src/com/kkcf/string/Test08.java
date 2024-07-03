package com.kkcf.string;

import java.util.Scanner;

public class Test08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");

        String str = sc.nextLine();

        sc.close();;

        String strReverse = new StringBuilder().append(str).reverse().toString();;

        System.out.println((str.equals(strReverse) ? "是" : "不是") + "对称字符串");
    }
}
