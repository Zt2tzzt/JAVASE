package com.kkcf.sort;

public class BubbleDemoSortDemo01 {
    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 3, 1};

        // 外循环：表示比较的轮数
        for (int i = 0; i < arr.length - 1; i++) {
            // 内循环：比较相邻的两个数，并找到最大值
            // -1 为了防止索引越界
            // -i 为了提高效率
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
