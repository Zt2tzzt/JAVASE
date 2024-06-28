package com.kkcf.array;

public class ArrayErrorDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        System.out.println(arr[10]);
        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5
        //at com.kkcf.array.ArrayErrorDemo01.main(ArrayErrorDemo01.java:8)
    }
}
