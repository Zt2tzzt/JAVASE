package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Demo09 {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "你好", "青蛙");

        Stream<String> concatStream = Stream.concat(list1.stream(), list2.stream());
        concatStream.forEach(s -> System.out.println(s));
    }
}
