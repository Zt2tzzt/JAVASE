package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo07 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌","张强","张三丰","张翠山","张良","王二麻子","谢广坤");

        list.stream().limit(3).forEach(s -> System.out.println(s));
        // 张无忌
        // 张强
        // 张三丰

        list.stream().skip(4).forEach(s -> System.out.println(s));
        // 张良
        // 王二麻子
        // 谢广坤

        // 获取“张三丰”、“张翠山”、“张良”这三个元素
        list.stream().skip(2).limit(3).forEach(s -> System.out.println(s));
    }
}
