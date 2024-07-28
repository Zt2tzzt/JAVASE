package com.kkcf.test;

import com.kkcf.javabean.Student;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test1 {
    public static void main(String[] args) {
        Student stu1 = new Student("zhagnsan", 23);
        Student stu2 = new Student("lisi", 24);
        Student stu3 = new Student("wangwu", 25);
        Student stu4 = new Student("wangwu", 25);

        HashMap<Student, String> map = new HashMap<>();

        map.put(stu1, "北京");
        map.put(stu2, "上海");
        map.put(stu3, "广州");
        map.put(stu4, "厦门");

        // 遍历方式一：键找值
        Set<Student> keys = map.keySet();

        for (Student key : keys) {
            String value = map.get(key);
            System.out.println(key + "=" + value);
        }

        // 遍历方式二：键值对
        Set<Map.Entry<Student, String>> entries = map.entrySet();

        for (Map.Entry<Student, String> entry : entries) {
            Student key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "=" + value);
        }

        // 遍历方式三：forEach + Lambda
        map.forEach((k, v) -> System.out.println(k + "=" + v));

        System.out.println(map);
    }
}
