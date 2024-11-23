package com.kkcf.reflect;

import java.lang.reflect.Constructor;

public class Demo02 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("com.kkcf.reflect.Student");

        // 获取 public 修饰的构造方法
        Constructor<?>[] cons = clazz.getConstructors();
        for (Constructor<?> con : cons)
            System.out.println(con);
        /*public com.kkcf.reflect.Student()
        public com.kkcf.reflect.Student(java.lang.String,int)*/

        // 获取所有的构造方法
        Constructor<?>[] allCons = clazz.getDeclaredConstructors();
        for (Constructor<?> con : allCons)
            System.out.println(con);
        /*public com.kkcf.reflect.Student()
        private com.kkcf.reflect.Student(int)
        protected com.kkcf.reflect.Student(java.lang.String)
        public com.kkcf.reflect.Student(java.lang.String,int)*/

        // 获取 public 修饰的空参构造方法
        Constructor<?> con1 = clazz.getConstructor();
        System.out.println(con1); // public com.kkcf.reflect.Student()

        // 获取带参构造方法，传入的参数，与带参构造的参数类型一致
        Constructor<?> con2 = clazz.getDeclaredConstructor(String.class);
        System.out.println(con2); // protected com.kkcf.reflect.Student(java.lang.String)

        Constructor<?> con3 = clazz.getDeclaredConstructor(int.class);
        System.out.println(con3); // private com.kkcf.reflect.Student(int)

        Constructor<?> con4 = clazz.getDeclaredConstructor(String.class, int.class);
        System.out.println(con4); // public com.kkcf.reflect.Student(java.lang.String,int)
    }
}
