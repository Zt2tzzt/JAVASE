package com.kkcf.string;

import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String str = sc.nextLine();

        int upCaseCount = 0;
        int lowCaseCount = 0;
        int numCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c >= 'A' && c <= 'Z')
                upCaseCount++;
            else if (c >= 'a' && c <= 'z')
                lowCaseCount++;
            else if (c >= '0' && c <= '9')
                numCount++;
        }

        System.out.println("大写字母：" + upCaseCount);
        System.out.println("小写字母：" + lowCaseCount);
        System.out.println("数字：" + numCount);
    }
}
