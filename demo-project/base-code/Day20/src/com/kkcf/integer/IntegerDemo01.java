package com.kkcf.integer;

public class IntegerDemo01 {
    public static void main(String[] args) {
        Integer i1 = new Integer(123);
        Integer i2 = new Integer("123");

        System.out.println(i1); // 123
        System.out.println(i2); // 123

        Integer i3 = Integer.valueOf(123);
        Integer i4 = Integer.valueOf("123");
        Integer i5 = Integer.valueOf("123", 8); // 八进制

        System.out.println(i3); // 123
        System.out.println(i4); // 123
        System.out.println(i4); // 123

    }
}
