package com.kkcf.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Demo09 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Method eatMethod = clazz.getDeclaredMethod("eat", String.class);

        int modifiers = eatMethod.getModifiers();
        System.out.println(modifiers); // 2

        String name = eatMethod.getName();
        System.out.println(name); // eat

        Parameter[] params = eatMethod.getParameters();
        for (Parameter param : params)
            System.out.println(param);

        // java.lang.String arg0

        Class<?>[] execpts = eatMethod.getExceptionTypes();
        for (Class<?> execpt : execpts)
            System.out.println(execpt);

        /*class java.io.IOException
        class java.lang.NullPointerException*/

        // 执行成员方法并获取返回值
        eatMethod.setAccessible(true); // 暴力反射
        Student stu = new Student("zzt", 18, "男");
        String result = (String) eatMethod.invoke(stu, "牛腩饭");
        System.out.println(result); // 奥利给
    }
}
