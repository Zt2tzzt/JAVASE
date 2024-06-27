package com.kkcf.process_control;

import java.util.Scanner;

public class WhileDemo02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入一个数字：");
        int num = sc.nextInt();
        sc.close();

        int numOrigin = num;

        int numReverse = 0; // 记录倒过来的结果

        while (num != 0) {
            int ge = num % 10;

            num = num / 10;

            numReverse = numReverse * 10 + ge;
        }

        System.out.println("numReverse = " + numReverse);
        System.out.println("numOrigin = " + numOrigin);
        System.out.println(numOrigin == numReverse ? "是回文数" : "不是回文数");
    }
}
