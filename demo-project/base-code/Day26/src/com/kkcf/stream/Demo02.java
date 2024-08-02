package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Demo02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        // 获取一条 Stream 流水线，并把集合中的数据，放到这条流水线上。
        Stream<String> stream = list.stream();

        // 使用终结方法，打印以下流水线上的所有数据
        stream.forEach(s -> System.out.println(s));
    }
}
