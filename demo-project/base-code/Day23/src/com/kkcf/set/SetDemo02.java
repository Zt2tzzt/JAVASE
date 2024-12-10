package com.kkcf.set;

import com.kkcf.javabean.Student;

import java.util.HashSet;

public class SetDemo02 {
    public static void main(String[] args) {
        Student stu1 = new Student("zhangsan", 23);
        Student stu2 = new Student("lisi", 24);
        Student stu3 = new Student("wangwu", 25);
        Student stu4 = new Student("zhangsan", 23);

        HashSet<Student> stus = new HashSet<>();
        stus.add(stu1);
        stus.add(stu2);
        stus.add(stu3);
        stus.add(stu4);

        System.out.println(stus);
    }
}
