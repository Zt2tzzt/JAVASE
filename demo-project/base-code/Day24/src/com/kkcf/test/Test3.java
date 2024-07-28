package com.kkcf.test;

import java.util.TreeMap;

public class Test3 {
    public static void main(String[] args) {
        // 升序
        TreeMap<Integer, String> map1 = new TreeMap<>();

        map1.put(1, "a");
        map1.put(2, "b");
        map1.put(3, "c");

        System.out.println(map1);

        // 降序
        TreeMap<Integer, String> map2 = new TreeMap<>((o1, o2) -> o2 - o1);

        map2.put(1, "a");
        map2.put(2, "b");
        map2.put(3, "c");

        System.out.println(map2);
    }
}
