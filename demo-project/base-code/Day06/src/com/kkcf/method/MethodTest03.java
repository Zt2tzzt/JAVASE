package com.kkcf.method;

public class MethodTest03 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int[] range = copyOfRange(arr, 2, 8);
        printArray(range);
    }

    public static int[] copyOfRange(int[] src, int from, int to) {
        int[] dest = new int[to - from];

        for (int i = 0; i < dest.length; i++)
            dest[i] = src[from + i];

        return dest;
    }

    // 打印数组
    public static void printArray(int[] arr) {
        System.out.print("[");

        for (int i = 0; i < arr.length; i++)
            System.out.print(i == arr.length - 1 ? arr[i] + "" : arr[i] + ", ");

        System.out.println("]");
    }

}
