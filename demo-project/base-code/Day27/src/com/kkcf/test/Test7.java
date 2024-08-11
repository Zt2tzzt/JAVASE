package com.kkcf.test;

import java.io.File;
import java.util.HashMap;

public class Test7 {
    public static HashMap<String, Integer> getCountMap(File src, HashMap<String, Integer> map) {

        if (src.isDirectory()) {
            File[] files = src.listFiles();

            if (files == null) return null;

            for (File file : files)
                getCountMap(file, map);
        } else {
            String name = src.getName();
            String[] split = name.split("\\.");
            String suffix = split[split.length - 1];

            map.put(suffix, map.containsKey(suffix) ? map.get(suffix) + 1 : 1);
        }

        return map;
    }

    public static void main(String[] args) {
        File src = new File("Day27/src/com/kkcf/test/aaa");
        HashMap<String, Integer> map = new HashMap<>();

        HashMap<String, Integer> result = getCountMap(src, map);
        if (result == null) return;

        result.forEach((key, val) -> System.out.println(key + ":" + val + "个"));
    }
}
