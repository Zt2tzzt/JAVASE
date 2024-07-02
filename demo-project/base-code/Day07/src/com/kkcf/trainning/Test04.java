package com.kkcf.trainning;

public class Test04 {
    public static void main(String[] args) {
        // 定义一个数组
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // 定义一个新数组
        int[] newArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++)
            newArr[i] = arr[i];

        // 打印新数组
        for (int i = 0; i < newArr.length; i++)
            System.out.print(newArr[i] + " ");
    }
}
