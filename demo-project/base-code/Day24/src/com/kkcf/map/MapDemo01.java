package com.kkcf.map;

import java.util.HashMap;
import java.util.Map;

public class MapDemo01 {
    public static void main(String[] args) {
        // 创建集合对象
        Map<String, String> map = new HashMap<>();

        map.put("mochizou", "tamako");
        map.put("oreki", "chitanda ");
        map.put("tsukamoto ", "kumiko");

        System.out.println(map); // {mochizou=tamako, oreki=chitanda , tsukamoto =kumiko}

        System.out.println(map.size()); // 3
    }
}
