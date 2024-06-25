package com.kkcf.Increment_decrement_operators;

import java.util.Scanner;

public class IncrementDecrementOperatorsTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你的衣服时髦度：");
        int me = sc.nextInt();

        System.out.println("请输入你的约会对象的衣服时髦度：");
        int girl = sc.nextInt();

        sc.close();

        boolean result = me > girl;

        System.out.println("约会" + (result ? "成功" : "失败"));
    }
}
