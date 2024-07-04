package com.kkcf.string;

import java.util.Random;

public class Test13 {
    public static void main(String[] args) {
        // 初始化英文字母数组
        char[] letterChars = new char[52]; // 26 个小写英文字母 + 26 个大写英文字母

        for (int i = 0; i < 26; i++) {
            letterChars[i] = (char) ('a' + i);
            letterChars[i + 26] = (char) ('A' + i);
        }

        // 初始化数字数字
        char[] numChars = new char[10];

        for (int i = 0; i < 10; i++)
            numChars[i] = (char) ('0' + i);

        // 随机生成字符串
        Random r = new Random();
        char[] resChars = new char[5];

        int len = resChars.length;

        for (int i = 0; i < len; i++)
            if (i != len - 1)
                resChars[i] = letterChars[r.nextInt(letterChars.length)];
            else
                resChars[i] = numChars[r.nextInt(numChars.length)];

        // 将最后一位数字，与前面随机一位字母对调。
        int randomIndex = r.nextInt(len);
        char temp = resChars[randomIndex];

        resChars[randomIndex] = resChars[len - 1];
        resChars[len - 1] = temp;

        String result = new String(resChars);
        System.out.println(result);
    }
}
