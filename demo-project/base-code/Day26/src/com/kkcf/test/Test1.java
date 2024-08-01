package com.kkcf.test;

import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
       /* List<String> list = List.of("张三", "李四", "王五", "赵六");

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        // 遍历结合
        for (String s : list) {
            System.out.println(s);
        }

        list.forEach(s -> System.out.println(s));*/

       /* Set<String> set = Set.of("张三", "李四", "王五", "赵六");

        for (String s : set)
            System.out.println(s);

        set.forEach(s -> System.out.println(s));*/

        Map<String, String> map = Map.of("张三", "南京", "李四", "北京", "王五", "上海",
                "赵六", "广州", "孙七", "深圳", "周八", "杭州",
                "吴九", "宁波", "郑十", "苏州", "刘一", "无锡",
                "陈二", "嘉兴");

        for (String key : map.keySet()) {
            String val = map.get(key);
            System.out.println(val);
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
