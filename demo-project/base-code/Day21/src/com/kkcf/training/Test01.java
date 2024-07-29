package com.kkcf.training;

import java.util.Arrays;

public class Test01 {
    public static void main(String[] args) {
        int[] arr = new int[12];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        // 打印数组
        System.out.println(Arrays.toString(arr)); // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144]
    }
}
