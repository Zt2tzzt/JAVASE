package com.kkcf.test;

import java.util.TreeMap;

public class Test5 {
    public static void main(String[] args) {
        String str = "aababcabcdabcde";

        // 统计
        TreeMap<Character, Integer> map = new TreeMap<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (map.containsKey(c)) {
                Integer count = map.get(c);
                map.put(c, count + 1);
            } else {
                map.put(c, 1);
            }
        }

        // 字符串拼接
        StringBuilder sb = new StringBuilder();

        for (Character key : map.keySet()) {
            sb.append(key).append("(").append(map.get(key)).append(")");
        }

        System.out.println(sb);
    }
}
