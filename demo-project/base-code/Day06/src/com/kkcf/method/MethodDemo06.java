package com.kkcf.method;

public class MethodDemo06 {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30};

        System.out.println(arr[1]); // 10
        change(arr);
        System.out.println(arr[1]); // 200
    }

    public static void change(int[] arr) {
        arr[1] = 200;
    }
}
