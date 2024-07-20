package com.kkcf.sort;

public class SelectionSortDemo01 {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 3, 1};

        // 外循环：表示比较的轮数
        // i 表示在这一轮比较中，用哪个索引与后面的元素进行比较
        for (int i = 0; i < arr.length - 1; i++) {
            // 内循环：使用 i 索引对应的元素，与后面的元素比较，小的放前面，大的放后面。
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        printArr(arr);
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
