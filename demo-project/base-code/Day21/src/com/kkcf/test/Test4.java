package com.kkcf.test;

public class Test4 {
    public static void main(String[] args) {
        int result = recursion(20);

        System.out.println(result); // 10946
    }

    /**
     * 此方芳用于，递归计算爬楼梯的方式
     * @param stairs 楼梯的台阶数
     * @return 爬楼梯有的方式有几种
     */
    public static int recursion(int stairs) {
        if (stairs == 1) return 1;
        if (stairs == 2) return 2;
        return recursion(stairs - 1) + recursion(stairs - 2);
    }
}
