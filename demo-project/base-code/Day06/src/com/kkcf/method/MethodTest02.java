package com.kkcf.method;

public class MethodTest02 {
    public static void main(String[] args) {
        double sea1 = getSum(10, 20, 30);
        double sea2 = getSum(30, 10, 20);
        double sea3 = getSum(10, 20, 29);
        double sea4 = getSum(10, 40, 20);

        double year = sea1 + sea2 + sea3 + sea4;
        System.out.println("年销售总额为" + year);
    }

    public static double getSum(double mon1, double mon2, double mon3) {
        return mon1 + mon2 + mon3;
    }

}
