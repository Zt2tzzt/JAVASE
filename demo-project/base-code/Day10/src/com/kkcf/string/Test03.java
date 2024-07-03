package com.kkcf.string;

public class Test03 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        System.out.println(arr2String(arr));
    }

    public static String arr2String(int[] arr) {
        if (arr == null) return "";

        if (arr.length == 0) return "[]";

        String str = "[";

        for (int i = 0; i < arr.length; i++)
            str += i != arr.length - 1 ? (arr[i] + ", ") : (arr[i]);

        str += "]";

        return str;
    }
}
