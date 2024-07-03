package com.kkcf.string;

import java.util.StringJoiner;

public class Demo06 {
    public static void main(String[] args) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        sj.add("aaa").add("bbb").add("ccc");

        System.out.println(sj.length()); // 15

        System.out.println(sj.toString().equals("haha")); // [aaa, bbb, ccc]
    }
}
