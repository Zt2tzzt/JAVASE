package com.kkcf.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class MyArraysDemo02 {
    public static void main(String[] args) {
        Integer[] arr = {2, 3, 1, 5, 6, 7, 8, 4, 9};

        Arrays.sort(arr, (o1, o2) -> {
            return o1 - o2;
        });

        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}