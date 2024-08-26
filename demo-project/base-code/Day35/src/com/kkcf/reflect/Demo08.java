package com.kkcf.reflect;

import java.lang.reflect.Method;

public class Demo08 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取所有的 public 修饰的方法对象，包含父类中的方法
        Method[] methods1 = clazz.getMethods();
        for (Method method : methods1)
            System.out.println(method);

        System.out.println("-----------------------------------------------------");

        // 获取所有的方法对象
        Method[] methods2 = clazz.getDeclaredMethods();
        for (Method method : methods2)
            System.out.println(method);

        System.out.println("-----------------------------------------------------");

        // 获取单个指定的公共方法对象
        Method sleepMethod = clazz.getMethod("sleep");
        System.out.println(sleepMethod); // public void com.kkcf.reflect.Student.sleep()

        // 获取单个指定的方法对象
        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);
        System.out.println(eatMethod); // private java.lang.String com.kkcf.reflect.Student.eat(java.lang.String) throws java.io.IOException,java.lang.NullPointerException
    }
}
