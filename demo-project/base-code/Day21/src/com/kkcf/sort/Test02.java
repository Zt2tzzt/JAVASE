package com.kkcf.sort;

public class Test02 {
    public static void main(String[] args) {
        System.out.println(getSum(100));
    }

    public static int getSum(int num) {
        if (num == 1) return 1;
        return num + getSum(num - 1);
    }
}
