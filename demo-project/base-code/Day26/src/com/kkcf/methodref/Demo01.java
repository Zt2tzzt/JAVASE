package com.kkcf.methodref;

import java.util.Arrays;
import java.util.Comparator;

public class Demo01 {
    public static void main(String[] args) {
        Integer[] arr = {4, 3, 8, 6, 9, 7, 2, 1};

        // 匿名内部类
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // Lambda 表达式
        Arrays.sort(arr, (o1, o2) -> o1 - o2);

        // 方法引用
        Arrays.sort(arr, Demo01::compare);
        System.out.println(Arrays.toString(arr));
    }

    public static int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
}
