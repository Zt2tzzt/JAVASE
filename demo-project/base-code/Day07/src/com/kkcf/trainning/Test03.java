package com.kkcf.trainning;

import java.util.Random;

public class Test03 {
    public static void main(String[] args) {
        char[] chars = new char[52]; // a-z A-Z 共 52 个字母

        // 填充数组
        for (int i = 0; i < chars.length; i++) {
            if (i < 26)
                chars[i] = (char) ('a' + i);
            else
                chars[i] = (char) ('A' + i - 26);
        }

        // 打印数组
        for (int i = 0; i < chars.length; i++)
            System.out.print(chars[i] + " ");
        System.out.println();

        // 获取验证码
        Random r = new Random();

        String code = "";

        // 前四位大写或小写字母
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(chars.length);
            code += chars[index];
        }

        // 后一位数字
        int randomNum = r.nextInt(10);
        code += randomNum;

        System.out.println(code);
    }
}
