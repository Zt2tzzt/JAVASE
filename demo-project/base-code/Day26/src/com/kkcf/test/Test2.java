package com.kkcf.test;

import java.util.HashMap;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("张三", "南京");
        map.put("李四", "北京");
        map.put("王五", "上海");
        map.put("赵六", "北京");
        map.put("孙七", "深圳");
        map.put("周八", "杭州");
        map.put("吴九", "宁波");
        map.put("郑十", "苏州");
        map.put("刘一", "无锡");
        map.put("陈二", "嘉兴");
        map.put("aaa", "111");

        /*Set<Map.Entry<String, String>> entrySet = map.entrySet();

        Map.Entry[] arr = new Map.Entry[0];

        // toArray 方法，在底层会比较集合长度，数组长度两者的大小
        //  - 集合的长度 > 数组的长度，会根据实际数据的个数，重新创建数组；
        //  - 集合的长度 <= 数组的长度：不会创建新的数组，而是直接用原数组。
        Map.Entry[] entries = entrySet.toArray(arr);

        // 创建不可变集合
        Map immutableMap = Map.ofEntries(entries);

        immutableMap.forEach((k, v) -> System.out.println(k + ":" + v));*/

        // 一行代码传，创建不可变集合
        /*Map<String, String> immutableMap = Map.ofEntries(map.entrySet().toArray(new Map.Entry[0]));

        immutableMap.put("bbb", "222"); // 报错

        immutableMap.forEach((k, v) -> System.out.println(k + ":" + v));*/
        Map<String, String> vimmutableMap = Map.copyOf(map);

        vimmutableMap.put("bbb", "222"); // 报错

        vimmutableMap.forEach((k, v) -> System.out.println(k + ":" + v));

    }
}
