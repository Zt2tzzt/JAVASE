package com.kkcf.string;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        String accurateUsername = "zhangsan";
        String accuratePassword = "123456";

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名：");
            String username = sc.next();
            System.out.println("请输入密码：");
            String password = sc.next();

            if (accurateUsername.equals(username) && accuratePassword.equals(password)) {
                System.out.println("登录成功");
                break;
            } else {
                System.out.println(i == 2 ? "账号被锁定，请联系管理员" : "登录失败，还剩下 " + (2 - 1) + " 次机会");
            }
        }
    }
}
