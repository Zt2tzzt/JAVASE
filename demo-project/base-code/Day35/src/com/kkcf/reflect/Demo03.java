package com.kkcf.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

public class Demo03 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        Constructor<?> con = clazz.getConstructor(String.class, int.class);

        int modifiers = con.getModifiers();
        System.out.println(modifiers); // 1

        Parameter[] params = con.getParameters();
        for (Parameter param : params)
            System.out.println(param);

        /*java.lang.String arg0
        int arg1*/
    }
}
