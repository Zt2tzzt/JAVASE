package com.kkcf.search;

public class FibonacciSearchDemo01 {
    // 获取斐波那契数列中的第 n 个数
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 斐波那契查找算法
    public static int fibonacciSearch(int[] array, int key) {
        int n = array.length;

        // 找到第一个大于或等于 n 的斐波那契数
        int fibMMm2 = 0; // (m-2)'th Fibonacci No.
        int fibMMm1 = 1; // (m-1)'th Fibonacci No.
        int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        // 标记用于已查找范围
        int offset = -1;

        while (fibM > 1) {
            // 检查有效范围内的第一个小于 fibM-2 的位置
            int i = Math.min(offset + fibMMm2, n - 1);

            // 如果 key 大于 array[i]，切换至右子数组
            if (array[i] < key) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            }
            // 如果 key 小于 array[i]，切换至左子数组
            else if (array[i] > key) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            }
            // 找到目标值
            else {
                return i;
            }
        }

        // 如果最后一个元素是目标值
        if (fibMMm1 == 1 && array[offset + 1] == key) {
            return offset + 1;
        }

        // 未找到目标值
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100};
        int key = 85;

        int index = fibonacciSearch(array, key);

        if (index != -1) {
            System.out.println("Element found at index " + index);
        } else {
            System.out.println("Element not found");
        }
    }
}

