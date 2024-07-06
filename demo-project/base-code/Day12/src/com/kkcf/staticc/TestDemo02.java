package com.kkcf.staticc;

import java.util.ArrayList;

public class TestDemo02 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();


        list.add(new Student("张三", 18, '男'));
        list.add(new Student("李四", 19, '男'));
        list.add(new Student("王五", 20, '男'));


        int age = StudentUtil.getMaxAgeStu(list);
        System.out.println(age);
    }
}
