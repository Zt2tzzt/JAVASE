package com.kkcf.exception;

import com.kkcf.javabean.Student;

public class Demo03 {
    public static void main(String[] args) {
        Student stu = new Student("张三-23");

        stu.setAge(66);

        System.out.println(stu);
    }
}
