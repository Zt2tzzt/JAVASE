package com.kkcf.regexp;

public class RegexDemo08 {
    public static void main(String[] args) {
        String regex3 = "((.)\\2*).+\\1";

        System.out.println("aaa123aaa".matches(regex3));
        System.out.println("bbb456bbb".matches(regex3));
        System.out.println("111789111".matches(regex3));
        System.out.println("&&abc&&".matches(regex3));
        System.out.println("aaa123aab".matches(regex3));
    }
}
