package com.kkcf.staticc;

public class TestDemo {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        String str = ArrUtil.printArr(arr1);
        System.out.println(str);

        double[] arr2 = {1.1, 2.2, 3.3, 4.4, 5.5};
        double average = ArrUtil.getAerage(arr2);
        System.out.println(average);
    }
}
