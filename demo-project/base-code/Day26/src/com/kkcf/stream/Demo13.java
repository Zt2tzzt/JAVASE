package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Demo13 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-男-16", "周芷若-女-17", "赵敏-女-18", "张强-男-19", "张三丰-男-20", "张翠山-男-21", "张良-男-22", "王二麻子-男-23", "谢广坤-男-24");

        // 收集 List 集合中，所有带“男”的元素，放入一个新的 ArrayList 集合中。
        /*List<String> newList = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toList());*/

        //System.out.println(newList);

        // 收集 List 集合中，所有带“女”的元素，到一个新的 HashSet 集合中。
        /*Set<String> newSet = list.stream()
                        .filter(s -> "女".equals(s.split("-")[1]))
                        .collect(Collectors.toSet());*/

        //System.out.println(newSet); // [周芷若-女-17, 赵敏-女-18]

        // 收集 List 集合中，所有带“女”的元素，到一个新的 HashMap 集合中，键为姓名，值为年龄。
        /*Map<String, Integer> newMap = list.stream()
                .filter(s -> "女".equals(s.split("-")[1]))
                .collect(Collectors.toMap(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.split("-")[0];
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s.split("-")[2]);
                    }
                }));*/

        Map<String, Integer> newMap = list.stream()
                .filter(s -> "女".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2]))
                );

        System.out.println(newMap);
    }
}
