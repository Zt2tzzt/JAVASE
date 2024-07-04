package com.kkcf.string;

public class Test14 {
    public static void main(String[] args) {
        String numStr1 = "123";
        String numStr2 = "456789";

        // 遍历字符串，得到每一位的数字字符。
        int num1 = 0;
        for (int i = 0; i < numStr1.length(); i++) {
            char c = numStr1.charAt(i);
            int n = c - '0';
            num1 = num1 * 10 + n;
        }

        int num2 = 0;
        for (int i = 0; i < numStr2.length(); i++) {
            char c = numStr2.charAt(i);
            int n = c - '0';
            num2 = num2 * 10 + n;
        }

        System.out.println(num1 * num2);
    }
}
