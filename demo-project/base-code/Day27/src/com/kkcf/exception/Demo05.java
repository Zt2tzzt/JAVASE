package com.kkcf.exception;

public class Demo05 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        try {
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException err) {
            System.out.println(err); // java.lang.ArrayIndexOutOfBoundsException: Index 10 out of bounds for length 5
        }

        System.out.println("看看我执行了吗？"); // 打印了
    }
}
