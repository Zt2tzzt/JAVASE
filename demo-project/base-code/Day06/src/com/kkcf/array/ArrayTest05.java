package com.kkcf.array;

import java.util.Random;

public class ArrayTest05 {
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = {1, 2, 3, 4, 5};

        // 数组索引交换
        int temo;
        for (int i = 0; i < arr.length; i++) {
            // 获取 i 后面的随机一个索引。
            int randomIndex = r.nextInt((arr.length - 1) - i + 1) + i;

            System.out.println("i = " + i + ", randomIndex = " + randomIndex);

            temo = arr[i];
            arr[i] = arr[randomIndex];
            arr[randomIndex] = temo;
        }

        // 打印这个数组
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            String ele = i != arr.length - 1 ? arr[i] + ", " : arr[i] + "";
            System.out.print(ele);
        }
        System.out.println("]");
    }
}
