package com.kkcf.regexp;

public class RegexpDemo01 {
    public static void main(String[] args) {
        String qqStr = "123e456";

        boolean flag = qqStr.matches("^[1-9]\\d{4,14}$");
        System.out.println(flag); // false
    }
}
