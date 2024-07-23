package com.kkcf.test;

import com.kkcf.generics.MyArrayList;

public class Test02 {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        boolean flag = list.add("haha");
        System.out.println(flag); // true

        String str = list.get(0);
        System.out.println(str); // haha

        System.out.println(list.toString()); // [haha, null, null, null, null, null, null, null, null, null]
    }
}
