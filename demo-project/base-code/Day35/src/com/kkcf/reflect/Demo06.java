package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo06 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Field nameField = clazz.getDeclaredField("name");

        // 获取成员变量权限修饰符
        int modifiers = nameField.getModifiers();
        System.out.println(modifiers); // 2

        // 获取成员变量名
        String name = nameField.getName();
        System.out.println(name); // name

        // 获取成员变量类型
        Class<?> type = nameField.getType();
        System.out.println(type); // class java.lang.String
    }
}
