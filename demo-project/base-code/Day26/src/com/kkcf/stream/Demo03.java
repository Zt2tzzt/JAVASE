package com.kkcf.stream;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Demo03 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("001", "张三");
        map.put("002", "李四");
        map.put("003", "王五");
        map.put("004", "赵六");

        // 方式一
        Stream<String> stream1 = map.keySet().stream();
        stream1.forEach(s -> System.out.println(s));

        // 方式二
        Stream<Map.Entry<String, String>> stream2 = map.entrySet().stream();
        stream2.forEach(s -> System.out.println(s.getKey() + ":" + s.getValue()));
    }
}
