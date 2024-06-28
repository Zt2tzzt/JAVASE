package com.kkcf.array;

public class StaticInitialize {
    public static void main(String[] args) {
        // 年龄
        int[] ages1 = new int[]{18, 22, 21, 19, 23};
        int[] ages2 = {18, 22, 21, 19, 23};

        // 姓名
        String[] names1 = new String[]{"zhangsan", "lisi", "wangwu"};
        String[] names2 = {"zhangsan", "lisi", "wangwu"};

        // 身高
        double[] heights1 = new double[]{1.78, 1.68, 1.69, 1.75, 1.70};
        double[] heights2 = {1.78, 1.68, 1.69, 1.75, 1.70};

        System.out.println(ages1); // [I@4eec7777
    }
}
