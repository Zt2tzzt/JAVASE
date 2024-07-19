package com.kkcf.integer;

public class IntegerDemo05 {
    public static void main(String[] args) {
        String str1 = Integer.toBinaryString(100);
        String str2 = Integer.toOctalString(100);
        String str3 = Integer.toHexString(100);

        System.out.println(str1); // 1100100
        System.out.println(str2); // 144
        System.out.println(str3); // 64

        int i1 = Integer.parseInt("123");
        System.out.println(i1); // 123
        System.out.println(i1 + 1); // 124

        boolean flag = Boolean.parseBoolean("true");
        System.out.println(flag); // true
    }
}
