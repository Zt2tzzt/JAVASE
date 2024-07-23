package com.kkcf.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class MyCollectionDemo03 {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();

        coll.add("aaa");
        coll.add("ccc");
        coll.add("ddd");

        coll.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        coll.forEach(s -> System.out.println(s));

        //System.out.println(coll); // [aaa, ccc, ddd]
    }
}
