package com.kkcf.ternary_operator;

import java.util.Scanner;

public class TernaryOperatorTest01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入第一只老虎的体重：");
        int weight1 = sc.nextInt();

        System.out.println("请输入第二只老虎的体重：");
        int weight2 = sc.nextInt();

        sc.close();

        System.out.println(weight1 == weight2 ? "它们的体重相等" : "它们的体重不相等");
    }
}
