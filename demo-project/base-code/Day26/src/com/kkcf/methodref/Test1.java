package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(List.of("1", "2", "3", "4", "5"));

        /*List<Integer> newList = list.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).collect(Collectors.toList());*/

        List<Integer> newList = list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(newList); // [1, 2, 3, 4, 5]
    }
}
