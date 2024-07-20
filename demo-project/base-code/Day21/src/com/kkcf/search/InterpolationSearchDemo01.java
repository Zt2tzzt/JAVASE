package com.kkcf.search;

public class InterpolationSearchDemo01 {

    public static void main(String[] args) {
        int[] array = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
        int key = 18;

        int index = interpolationSearch(array, key);

        if (index != -1)
            System.out.println("Element found at index " + index);
        else
            System.out.println("Element not found");

    }

    // 插值查找算法
    public static int interpolationSearch(int[] arr, int key) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max && key >= arr[min] && key <= arr[max]) {
            // 计算插值位置
            int pos = min + ((key - arr[min]) * (max - min)) / (arr[max] - arr[min]);

            // 找到目标值
            if (arr[pos] == key)
                return pos;

            // 如果目标值大于当前位置的值，则在右侧子数组中查找
            if (arr[pos] < key) {
                min = pos + 1;
            }
            // 如果目标值小于当前位置的值，则在左侧子数组中查找
            else {
                max = pos - 1;
            }
        }

        // 未找到目标值
        return -1;
    }
}
