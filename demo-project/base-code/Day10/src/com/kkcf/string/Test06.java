package com.kkcf.string;

public class Test06 {
    public static void main(String[] args) {
        String id = "430202200612124321";

        String year = id.substring(6, 10);
        String mon = id.substring(10, 12);
        String day = id.substring(12, 14);

        System.out.println("出生年月日：" + year + " 年 " + mon + " 月 " + day + " 日 ");

        char c = id.charAt(id.length() - 1);
        int num = c - '0';

        System.out.println("性别码：" + (num % 2 == 0 ? "女" : "男"));
    }
}
