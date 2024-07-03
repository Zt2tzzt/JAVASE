package com.kkcf.string;

public class Demo05 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String str = sb.append("aaa").append("bbb").append("ccc").append("ddd").reverse().toString();

        System.out.println(str.length()); // 12
        System.out.println(str); // dddcccbbbaaa
    }
}
