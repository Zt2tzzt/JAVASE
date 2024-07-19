package com.kkcf.test;

import java.util.ArrayList;
import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        do {
            System.out.println("请输入一个整数：");
            String str = sc.nextLine();

            int i = Integer.parseInt(str);

            // 边界判断
            if (i < 0 || i > 100) {
                System.out.println("输入有误，请重新输入：");
                continue;
            }

            // 添加数据时，触发了自动装箱
            list.add(i);
        } while (calcSum(list) < 200);
    }

    public static int calcSum(ArrayList<Integer> list) {
        int sum = 0;

        for (Integer num : list)
            sum += num;

        return sum;
    }
}
