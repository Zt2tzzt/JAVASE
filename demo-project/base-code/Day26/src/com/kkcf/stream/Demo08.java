package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo08 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张无忌", "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        list.stream().distinct().forEach(s -> System.out.println(s));
    }
}
