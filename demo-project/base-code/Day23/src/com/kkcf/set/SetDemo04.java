package com.kkcf.set;

import com.kkcf.javabean.Student;

import java.util.TreeSet;

public class SetDemo04 {
    public static void main(String[] args) {
        TreeSet<Student> stus = new TreeSet<>();

        Student stu3 = new Student("wangwu", 25);
        Student stu2 = new Student("lisi", 24);
        Student stu1 = new Student("zhangsan", 23);

        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);

        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);

        System.out.println(stus); // [Student{name='zhangsan', age=23}, Student{name='lisi', age=24}, Student{name='wangwu', age=25}]
    }
}
