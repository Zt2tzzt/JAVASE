package com.kkcf.collection;

import java.util.ArrayList;
import java.util.Collection;

public class MyCollectionDemo01 {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();

        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");

        int size = coll.size();
        System.out.println(size); // 3
    }
}
