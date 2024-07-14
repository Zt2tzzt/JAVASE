package com.kkcf.myapi;

import java.util.Objects;

public class ObjectsDemo01 {
    public static void main(String[] args) {
        //Student s1 = new Student("zhangsan", 23);
        //Student s2 = new Student("zhangsan", 23);
        //
        //System.out.println(Objects.equals(s1, s2)); // true

        Student s3 = new Student();
        Student s4 = null;

        System.out.println(Objects.nonNull(s3)); // true
        System.out.println(Objects.nonNull(s4)); // false
    }
}
