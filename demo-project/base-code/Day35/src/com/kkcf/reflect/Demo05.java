package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo05 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取所有成员变量
        Field[] fields1 = clazz.getDeclaredFields();
        for (Field field : fields1)
            System.out.println(field);

        /*public java.lang.String com.kkcf.reflect.Student.gender
        private java.lang.String com.kkcf.reflect.Student.name
        private int com.kkcf.reflect.Student.age*/

        // 获取所有公共成员变量
        Field[] fields2 = clazz.getFields();
        for (Field field : fields2)
            System.out.println(field);

        //public java.lang.String com.kkcf.reflect.Student.gender

        // 获取单个成员变量
        Field name = clazz.getDeclaredField("name");
        System.out.println(name); // private java.lang.String com.kkcf.reflect.Student.name

        // 获取单个公共的成员变量
        Field gender = clazz.getField("gender");
        System.out.println(gender); // public java.lang.String com.kkcf.reflect.Student.gender
    }
}
