package com.kkcf.array;

public class ArrayDynamicInitialization {
    public static void main(String[] args) {
        String[] names = new String[50];

        names[0] = "张三";
        names[1] = "李四";

        System.out.println(names[0]);
        System.out.println(names[1]);
        System.out.println(names[2]); // 数组初始化默认值：null
    }
}
