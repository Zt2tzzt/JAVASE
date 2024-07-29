package com.kkcf.training;

public class Test02 {
    public static void main(String[] args) {
        int result = recursion(12);

        System.out.println(result); // 144
    }

    public static int recursion(int month) {
        if (month == 1 || month == 2)
            return 1;
        else
            return recursion(month - 1) + recursion(month - 2);
    }
}
