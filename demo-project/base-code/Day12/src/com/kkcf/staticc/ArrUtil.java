package com.kkcf.staticc;

import java.util.StringJoiner;

public class ArrUtil {
    private ArrUtil() {}

    public static String printArr(int[] arr) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        for (int j : arr) sj.add(String.valueOf(j));

        return sj.toString();
    }

    public static double getAerage(double[] arr) {
        double sum = 0;

        for (double v : arr) sum += v;

        return sum / arr.length;
    }
}
