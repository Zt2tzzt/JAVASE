package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class Demo06 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌","张强","张三丰","张翠山","张良","王二麻子","谢广坤");

        list.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.startsWith("张");
                    }
                })
                .forEach(s -> System.out.println(s));

        System.out.println(list); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]
    }
}
