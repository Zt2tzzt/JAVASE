package com.kkcf.string;

import java.util.Random;
import java.util.Scanner;

public class Test12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个字符串：");
        String str = sc.next();

        sc.close();

        char[] chars = str.toCharArray();

        Random r = new Random();

        for (int i = 0; i < chars.length; i++) {
            int index = r.nextInt(chars.length);

            char temp = chars[i];
            chars[i] = chars[index];
            chars[index] = temp;
        }

        System.out.println(new String(chars));
    }
}
