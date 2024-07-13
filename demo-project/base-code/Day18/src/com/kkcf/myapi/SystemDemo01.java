package com.kkcf.myapi;

public class SystemDemo01 {
    public static void main(String[] args) {
        // 传 0，表示正常停止
        // 传非 0，表示异常停止
        //System.exit(0);

        //System.out.println("haha");

        //System.out.println(System.currentTimeMillis()); // 1720863235923

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = new int[10];

        // 从 0 索引的位置拷贝 arr1 到 arr2 的 0 索引上，拷贝 arr1.length 个元素。
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
    }
}
