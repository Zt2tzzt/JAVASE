package com.kkcf.variableparam;

public class Demo01 {
    public static int getSum(int[] arr) {
        int sum = 0;

        for (int i : arr)
            sum += i;

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        int sum = getSum(arr);

        System.out.println(sum); // 15
    }
}
