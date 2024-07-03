package com.kkcf.string;

import java.util.Scanner;

public class Test04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个字符串：");
        String str = sc.nextLine();

        String result = reverse(str);
        System.out.println(result);
    }

    public static String reverse(String str) {
        int len = str.length();

        char[] chs = new char[len];

        for (int i = len - 1; i >= 0; i--) {
            char c = str.charAt(i);
            chs[len - 1 - i] = c;
        }

        String result = new String(chs);

        return result;
    }
}
