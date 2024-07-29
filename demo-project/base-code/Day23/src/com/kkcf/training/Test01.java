package com.kkcf.training;

import com.kkcf.generics.ListUtil;

import java.util.ArrayList;

public class Test01 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        ListUtil.addAll(list, "hello", "world", "java");
        System.out.println(list); // [hello, world, java]

        ArrayList<Integer> list2 = new ArrayList<>();
        ListUtil.addAll(list2, 1, 2, 3);
        System.out.println(list2); // [1, 2, 3]
    }
}
