package com.kkcf.string;

public class Demo07 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        System.out.println(sb.capacity()); // 16
        System.out.println(sb.length()); // 0

        sb.append("abc");

        System.out.println(sb.capacity()); // 16
        System.out.println(sb.length()); // 3

        sb.append("defghijklmnopqrstuvwxyz");

        System.out.println(sb.capacity()); // 34
        System.out.println(sb.length()); // 26

        StringBuilder sb2 = new StringBuilder();
        sb2.append("abcdefghijklmnopqrstuvwxyz0123456789");

        System.out.println(sb2.capacity()); // 36
        System.out.println(sb2.length()); // 36
    }
}
