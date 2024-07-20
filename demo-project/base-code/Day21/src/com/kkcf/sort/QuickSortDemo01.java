package com.kkcf.sort;

import java.util.Arrays;

public class QuickSortDemo01 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8};

        quickSort(arr, 0, arr.length - 1);

        printArr(arr);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int start = left;
        int end = right;

        // 递归出口
        if (start > end) return;

        // 记录基准数
        int pivot = arr[start];

        // 找到要交换的数字
        while (start != end) {
            while (end > start && arr[end] >= pivot) {
                end--;
            }

            while (end > start && arr[start] <= pivot) {
                start++;
            }

            // left、right 索引指向的元素进行交换
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }

        // 基准数归位：将 left 索引指向的基准数，与 start、end 索引指向元素交换
        int temp = arr[left];
        arr[left] = arr[start];
        arr[start] = temp;

        // 确定基准数左边的范围，重复刚刚所做的事情
        quickSort(arr, left, start - 1);

        // 确定基准数右边的范围，重复刚刚所做的事情
        quickSort(arr, start + 1, right);
    }

    /**
     * 此方法用于，打印数组
     * @param arr 要打印的数组
     */
    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
