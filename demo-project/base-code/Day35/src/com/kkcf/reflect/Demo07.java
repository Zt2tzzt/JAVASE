package com.kkcf.reflect;

import java.lang.reflect.Field;

public class Demo07 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");
        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true); // 暴力反射

        Student stu = new Student("张三", 23, "男");

        // 获取 private 修饰的属性记录的值
        String nameVal = (String) nameField.get(stu);
        System.out.println(nameVal); // 张三

        // 修改 private 修饰的属性记录的值
        nameField.set(stu, "李四");
        System.out.println(stu); // Student{name='李四', age=23, gender='男'}
    }
}
