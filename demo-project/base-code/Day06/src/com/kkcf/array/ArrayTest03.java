package com.kkcf.array;

import java.util.Random;

public class ArrayTest03 {
    public static void main(String[] args) {
        Random r = new Random();

        // 定义数组
        int[] nums = new int[10];

        // 填充随机数
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100) + 1; // 0-100
        }

        // 打印这个数组
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            String ele = i != nums.length - 1 ? nums[i] + ", " : nums[i] + "";
            System.out.print(ele);
        }
        System.out.println("]");

        // 求和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        System.out.println("和为 " + sum);

        // 求平均数
        double average = (double) sum / nums.length;
        System.out.println("平均数为 " + average);

        // 统计有多少数字小于平均数
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < average) count++;
        }
        System.out.println("小于平均数的个数为 " + count);
    }
}
