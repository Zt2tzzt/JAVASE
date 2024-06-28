package com.kkcf.memory;

public class ArrayMemory02 {
    public static void main(String[] args) {
        int[] arr1 = {11, 22};
        int[] arr2 = arr1;

        System.out.println(arr1[0]); // 11
        System.out.println(arr2[0]); // 11

        arr2[0] = 33;

        System.out.println(arr1[0]); // 33
        System.out.println(arr2[0]); // 33
    }
}
