package com.kkcf.sort;

public class Test03 {
    public static void main(String[] args) {
        System.out.println(getfactorial(5));
    }

    public static int getfactorial(int num) {
        if (num == 1) return 1;
        return num * getfactorial(num - 1);
    }
}
