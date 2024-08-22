package com.kkcf.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 加载配置文件
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream("Day35/src/com/kkcf/test/prop.properties");
        p.load(fis);
        fis.close();

        // 获取全类名，方法名
        String classname = (String) p.get("classname");
        String method = (String) p.get("method");

        // 利用反射，创建对象
        Class<?> clazz = Class.forName(classname);
        Constructor<?> con = clazz.getDeclaredConstructor();
        Object o = con.newInstance();

        // 利用反射，并调用方法
        Method meth = clazz.getDeclaredMethod(method);
        meth.setAccessible(true);
        meth.invoke(o);
    }
}
