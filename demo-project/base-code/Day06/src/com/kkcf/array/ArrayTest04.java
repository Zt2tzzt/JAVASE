package com.kkcf.array;

public class ArrayTest04 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        int i = 0;
        int j = arr.length - 1;
        int temp;

        for (; i != j; i++, j--) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        // 打印这个数组
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            String ele = x != arr.length - 1 ? arr[x] + ", " : arr[x] + "";
            System.out.print(ele);
        }
        System.out.println("]");
    }
}
