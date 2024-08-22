package com.kkcf.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo04 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取 public 修饰的构造方法，并创建对象
        Constructor<?> con1 = clazz.getConstructor(String.class, int.class);
        Student stu = (Student) con1.newInstance("张三", 18);
        System.out.println(stu);

        // 获取 private 修饰的构造方法，并创建对象
        Constructor<?> con2 = clazz.getDeclaredConstructor(int.class);
        con2.setAccessible(true); // 暴力反射：表示临时取消权限校验
        Student stu2 = (Student) con2.newInstance(18);
        System.out.println(stu2);
    }
}
