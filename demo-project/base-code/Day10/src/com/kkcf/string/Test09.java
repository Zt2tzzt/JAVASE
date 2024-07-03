package com.kkcf.string;

public class Test09 {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3};

        System.out.println(arr2String(arr));
    }

    public static String arr2String(int[] arr) {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < arr.length; i++)
            sb.append(i == arr.length - 1 ? arr[i] : arr[i] + ",");

        sb.append("]");

        return sb.toString();
    }
}
