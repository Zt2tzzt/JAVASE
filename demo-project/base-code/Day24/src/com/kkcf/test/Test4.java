package com.kkcf.test;

import com.kkcf.javabean.Student;

import java.util.TreeMap;
import java.util.TreeSet;

public class Test4 {
    public static void main(String[] args) {
        TreeMap<Student, String> map = new TreeMap<>();

        map.put(new Student("zhangsan", 23), "北京");
        map.put(new Student("lisi", 24), "上海");
        map.put(new Student("wangwu", 25), "广州");
        map.put(new Student("zhaoliu", 24), "深圳");

        System.out.println(map);
    }
}
