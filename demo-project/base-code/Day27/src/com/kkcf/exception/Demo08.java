package com.kkcf.exception;

public class Demo08 {
    // NullPointerException, ArrayIndexOutOfBoundsException 都是运行时异常，所以没必要使用 throws
    //public static int getMax(int[] arr) throws NullPointerException, ArrayIndexOutOfBoundsException {
    public static int getMax(int[] arr) {
        if (arr == null) {
            throw new NullPointerException("数组不能为 null");
        }
        if (arr.length == 0) {
            throw new ArrayIndexOutOfBoundsException("数组长度不能为0");
        }

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        return max;
    }
    public static void main(String[] args) {
        int[] arr1 = null;
        try {
            int max1 = getMax(arr1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] arr2 = {};
        try {
            int max2 = getMax(arr2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
