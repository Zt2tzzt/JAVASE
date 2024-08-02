package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo10 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌-15", "张强-20", "张三丰-30", "张翠山-40", "张良-50", "王二麻子-60", "谢广坤-70");

        /*list.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                String[] arr = s.split("-");
                Integer i = Integer.parseInt(arr[1]);
                return i;
            }
        }).forEach(i -> System.out.println(i));*/

        list.stream()
                .map(s -> Integer.parseInt(s.split("-")[1]))
                .forEach(i -> System.out.println(i));
    }
}
