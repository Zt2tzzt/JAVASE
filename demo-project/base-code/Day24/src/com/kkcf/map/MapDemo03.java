package com.kkcf.map;

import java.util.LinkedHashMap;

public class MapDemo03 {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("c", 321);
        map.put("b", 123);
        map.put("a", 789);

        System.out.println(map); // {c=321, b=123, a=789}
    }
}
