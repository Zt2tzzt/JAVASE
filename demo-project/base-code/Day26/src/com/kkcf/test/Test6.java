package com.kkcf.test;

import java.util.HashMap;
import java.util.Map;

public class Test6 {
    public static void main(String[] args) {
        // 使用匿名内部类初始化 HashMap
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("apple", 1);
            put("banana", 2);
            put("cherry", 3);
        }};

        // 打印 HashMap 的内容
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }
}
