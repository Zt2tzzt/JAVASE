package com.kkcf.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo04 {
    public static void main(String[] args) {
        // 基本数据类型数组
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        IntStream stream1 = Arrays.stream(arr1);

        stream1.forEach(i -> System.out.println(i));

        // 引用数据类型数组
        String[] arr2 = {"a", "b", "c", "d", "e"};

        Stream<String> stream2 = Arrays.stream(arr2);

        stream2.forEach(s -> System.out.println(s));
    }
}
