package com.kkcf.training;

public class Test03 {
    public static void main(String[] args) {
        int result1 = recursion1(10, 1);
        System.out.println(result1); // 1534

        int result2 = recursion2(1);
        System.out.println(result2); // 1534
    }

    /**
     * 此方芳用于，递归计算
     *
     * @param day   最后一天的天数
     * @param count 最后一天剩下的桃子数
     * @return 第一天的桃子数
     */
    public static int recursion1(int day, int count) {
        if (day == 1) return count;

        return recursion1(day - 1, (count + 1) * 2);
    }

    /**
     * 此方法用于，递归计算
     *
     * @param day 第一天的天数
     * @return 第一天的桃子数
     */
    public static int recursion2(int day) {
        if (day == 10) return 1;

        return (recursion2(day + 1) + 1) * 2;
    }
}
