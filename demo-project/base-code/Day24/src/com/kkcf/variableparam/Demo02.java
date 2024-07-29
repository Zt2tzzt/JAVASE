package com.kkcf.variableparam;

public class Demo02 {
    private static int getSum(int...args) {
        System.out.println(args);

        int sum = 0;

        for (int arg : args)
            sum += arg;

        return sum;
    }


    public static void main(String[] args) {
        int sum = getSum(1, 2, 3, 4, 5);

        System.out.println(sum); // 15
    }
}
