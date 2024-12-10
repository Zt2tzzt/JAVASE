package com.kkcf.reflect;

public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 第一种方式
        Class<?> clazz1 = Class.forName("com.kkcf.reflect.Student");
        System.out.println(clazz1); // class com.kkcf.reflect.Student

        // 第二种方式
        Class<Student> clazz2 = Student.class;

        // 第三种方式
        Student stu = new Student("zzt", 18);
        Class<? extends Student> clazz3 = stu.getClass();

        System.out.println(clazz1 == clazz2); // true
        System.out.println(clazz2 == clazz3); // true
    }
}
