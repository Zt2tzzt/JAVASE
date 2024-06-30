package com.kkcf.trainning;

public class Test07 {
    public static void main(String[] args) {
        // 1.定义数组，记录解密之后的结果
        int[] nums = {8, 3, 4, 6};

        // 2. 反转
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }


        // 3. 0-9 的数 +5 范围是：5-14，再 %10，那么 0-4 就要 +10，5-9 不变
        for (int i = 0; i < nums.length; i++)
            if (nums[i] >= 0 && nums[i] <= 4)
                nums[i] += 10;

        // 4.每一位 -5
        for (int i = 0; i < nums.length; i++)
            nums[i] -= 5;

        // 5.拼接数组
        int result = 0;
        for (int i = 0; i < nums.length; i++)
            result = result * 10 + nums[i];

        System.out.println(result);
    }
}
