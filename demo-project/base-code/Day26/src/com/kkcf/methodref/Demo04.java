package com.kkcf.methodref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public class Demo04 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Integer[] arr = list.stream().toArray(Integer[]::new);

        System.out.println(Arrays.toString(arr));
    }
}
