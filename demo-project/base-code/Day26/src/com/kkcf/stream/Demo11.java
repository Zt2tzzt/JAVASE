package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Collections;

public class Demo11 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        /*list.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        list.stream().forEach(s -> System.out.println(s));*/

        long count = list.stream().count();
        System.out.println(count); // 7
    }
}
