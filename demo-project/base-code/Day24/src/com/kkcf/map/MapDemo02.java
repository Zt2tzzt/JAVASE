package com.kkcf.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class MapDemo02 {
    public static void main(String[] args) {
        // 创建集合对象
        Map<String, String> map = new HashMap<>();

        map.put("mochizou", "tamako");
        map.put("oreki", "chitanda ");
        map.put("tsukamoto ", "kumiko");

        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                System.out.println(key + "=" + value);
            }
        });

        map.forEach((key, value) -> System.out.println(key + "=" + value));
    }
}
