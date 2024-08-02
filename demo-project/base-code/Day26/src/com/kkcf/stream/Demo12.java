package com.kkcf.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.IntFunction;

public class Demo12 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "张强", "张三丰", "张翠山", "张良", "王二麻子", "谢广坤");

        Object[] arr1 = list.stream().toArray();
        System.out.println(Arrays.toString(arr1)); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]

        // 转数组时，指定数组的类型
        // IntFunction 的泛型，具体类型的数组
        // apply 方法的形参 int value：表示流中数据的个数，要跟数组的长度保持一致
        // apply 方法的返回值：表示具体类型的数组
        String[] array = list.stream().toArray(new IntFunction<String[]>() {
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });
        System.out.println(Arrays.toString(array)); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]

        // 使用 Lambda 表达式的方案
        String[] arr = list.stream().toArray(i -> new String[i]);
        System.out.println(Arrays.toString(arr)); // [张无忌, 张强, 张三丰, 张翠山, 张良, 王二麻子, 谢广坤]
    }
}
