package com.kkcf.test;

import java.io.File;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class Test7 {
    public static HashMap<String, Integer> getCountMap(File src, HashMap<String, Integer> map) {

        if (src.isDirectory()) {
            File[] files = src.listFiles();

            if (files == null) return null;

            for (File file : files) {
                if (file.isDirectory()) {
                    return getCountMap(file, map);
                } else {
                    String name = file.getName();
                    String[] split = name.split("\\.");
                    String suffix = split[split.length - 1];

                    map.put(suffix, map.containsKey(suffix) ? map.get(suffix) + 1 : 1);
                }
            }
        }

        return map;
    }

    public static void main(String[] args) {
        File src = new File("Day27/src/com/kkcf/test/aaa");
        HashMap<String, Integer> map = new HashMap<>();

        HashMap<String, Integer> result = getCountMap(src, map);
        if (result == null) return;
        result.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String key, Integer val) {
                System.out.println(key + ":" + val + "个");
            }
        });
    }
}
