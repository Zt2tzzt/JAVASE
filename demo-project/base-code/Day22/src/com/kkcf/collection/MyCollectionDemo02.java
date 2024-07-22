package com.kkcf.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MyCollectionDemo02 {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();

        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
        coll.add("eee");

        // 获取迭代器对象
        Iterator<String> it = coll.iterator();

        // 迭代器遍历
        while (it.hasNext()) {
            String str = it.next();

            if ("bbb".equals(str)) it.remove();
        }

        System.out.println(coll); // [aaa, ccc, ddd, eee]
    }
}
