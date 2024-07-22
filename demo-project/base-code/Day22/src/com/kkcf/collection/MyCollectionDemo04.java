package com.kkcf.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MyCollectionDemo04 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        // 获取列表迭代器
        ListIterator<String> lit = list.listIterator();

        // 使用迭代器遍历
        while (lit.hasNext()) {
            String s = lit.next();

            if ("bbb".equals(s)) lit.add("qqq");
        }

        System.out.println(list); // [aaa, bbb, qqq, ccc]
    }
}
