package com.kkcf.memory;

public class ArrayMemory {
    public static void main(String[] args) {
        int[] arr = new int[2];

        System.out.println(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);

        arr[0] = 11;
        arr[1] = 22;

        System.out.println(arr[0]);
        System.out.println(arr[1]);

        System.out.println("--------------------");

        int[] arr2 = {33, 44, 55};

        System.out.println(arr2);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
    }
}
