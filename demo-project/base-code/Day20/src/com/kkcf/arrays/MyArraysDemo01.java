package com.kkcf.arrays;

import java.util.Arrays;

public class MyArraysDemo01 {
    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 3, 1};

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5]
    }
}
