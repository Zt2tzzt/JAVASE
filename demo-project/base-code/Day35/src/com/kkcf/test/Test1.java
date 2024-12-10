package com.kkcf.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class Test1 {
    /**
     * 此方法用于：保存对象中所有字段的值到本地文件
     *
     * @param obj 实例对象
     */
    private static void saveObj(Object obj) throws IllegalAccessException, IOException {
        // 获取字节码文件对象
        Class<?> clazz = obj.getClass();

        BufferedWriter bw = new BufferedWriter(new FileWriter("Day35/src/com/kkcf/test/a.txt"));

        // 获取所有成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 暴力反射

            // 获取成员变量名称和保存的值
            String name = field.getName();
            Object val = field.get(obj);

            // 写出数据
            bw.write(name + "=" + val);
            bw.newLine();
        }

        bw.close();
    }

    public static void main(String[] args) throws IllegalAccessException, IOException {
        Student stu = new Student("zzt", 18, "男", 1.88, "喝咖啡");

        saveObj(stu);
    }
}
