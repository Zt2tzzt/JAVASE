package com.kkcf.training;

public class Test02 {
    public static void main(String[] args) {
        String str = "12345";

        boolean flag = str.matches("[0-9]\\d{0,9}");

        if (!flag) {
            System.out.println("数据格式错误");
            return;
        }

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            int num = c - '0';
            result = result * 10 + num;
        }

        System.out.println(result); // 12345
    }
}
