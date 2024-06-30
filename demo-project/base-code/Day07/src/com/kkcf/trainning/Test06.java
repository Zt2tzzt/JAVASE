package com.kkcf.trainning;

public class Test06 {
    public static void main(String[] args) {
        // 1.把整数（一个大于 0 的数，比如 1983）里的每一位，放到数组当中。
        int num = 1983;
        int temp1 = num;

        // 计算数字的位数
        int count = 0;

        while (temp1 > 0) {
            temp1 /= 10;
            count++;
        }

        // 初始化数组
        int[] nums = new int[count];

        int temp2 = num;
        for (int i = 0; i < nums.length; i++) {
            nums[nums.length - 1 - i] = temp2 % 10;
            temp2 /= 10;
        }

        // 打印 nums 数组
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println();

        // 2.加密：每位数 +5
        for (int i = 0; i < nums.length; i++)
            nums[i] += 5;

        // 3.加密：每位数 %10
        for (int i = 0; i < nums.length; i++)
            nums[i] %= 10;

        // 4.将所有数字反转
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        // 打印 nums 数组
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println();

        // 5.将数组中的数字，组成一个整数
        int result = 0;
        for (int i = 0; i < nums.length; i++)
            result = result * 10 + nums[i];
        System.out.println(result);
    }
}
