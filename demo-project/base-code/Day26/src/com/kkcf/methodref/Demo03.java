package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;

public class Demo03 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("aaa", "bbb", "ccc", "ddd"));

        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
