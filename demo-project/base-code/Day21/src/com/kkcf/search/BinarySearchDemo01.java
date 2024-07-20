package com.kkcf.search;

public class BinarySearchDemo01 {
    public static void main(String[] args) {
        int[] arr = {7, 23, 79, 81, 103, 127, 131, 147};

        System.out.println(InterpolationSearch(arr, 131));

    }

    public static int InterpolationSearch(int[] arr, int target) {
        int min = 0;
        int max = arr.length - 1;
        int mid;

        while (min < max) {
            mid = (min + max) / 2;

            if (arr[mid] > target) {
                max = mid - 1;
            } else if (arr[mid] < target) {
                min = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}

