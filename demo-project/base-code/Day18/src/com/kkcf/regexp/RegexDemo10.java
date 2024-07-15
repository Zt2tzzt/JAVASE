package com.kkcf.regexp;

public class RegexDemo10 {
    public static void main(String[] args) {
        String regex2 ="[1-9]\\d{16}(\\d Xx)\\1";
        String regex3 ="[1-9]\\d{16}(?:\\d Xx)\\1"; // 编译报错，?: 表示非捕获分组，不计算组号

        System.out.println("41080119930228457x".matches(regex3));
    }
}
