package com.kkcf.string;

public class Demo01 {
    public static void main(String[] args) {
        String s1 = new String("abc");
        String s2 = "Abc";

        System.out.println(s1 == s2); // false

        System.out.println(s1.equals(s2)); // false

        System.out.println(s1.equalsIgnoreCase(s2)); // true
    }
}
