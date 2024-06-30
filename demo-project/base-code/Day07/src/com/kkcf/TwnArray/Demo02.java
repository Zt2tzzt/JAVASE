package com.kkcf.TwnArray;

public class Demo02 {
    public static void main(String[] args) {
        int[][] arrArr = {
                {22, 66, 44},
                {77, 33, 88},
                {25, 45, 65},
                {11, 66, 99},
        };

        int yearSum = 0;

        for (int i = 0; i < arrArr.length; i++) {
            int[] seaArr = arrArr[i];

            int seaSum = getSeaSum(seaArr);
            System.out.println("第 " + (i + 1) + " 季度的营业额为 " + seaSum + " 万元");

            yearSum += seaSum;
        }
        System.out.println("全年营业额为 " + yearSum + " 万元");

    }

    public static int getSeaSum(int[] arr) {
        int sum = 0;

        for (int i = 0; i < arr.length; i++)
            sum += arr[i];

        return sum;
    }
}
