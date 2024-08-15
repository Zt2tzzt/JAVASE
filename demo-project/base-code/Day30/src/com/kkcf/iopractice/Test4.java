package com.kkcf.iopractice;

import java.io.*;
import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) throws IOException {
        // 读取文件中的数据
        BufferedReader br = new BufferedReader(new FileReader("Day30/src/com/kkcf/iopractice/userinfo.txt"));
        String line = br.readLine();
        br.close();

        String[] split = line.split("&");
        String[] arr1 = split[0].split("=");
        String[] arr2 = split[1].split("=");
        String[] arr3 = split[2].split("=");
        String accurateUsername = arr1[1];
        String accuratePassword = arr2[1];
        int count = Integer.parseInt(arr3[1]);

        Scanner sc = new Scanner(System.in);

        if (count > 2) {
            System.out.println("账号已被锁定");
            return;
        }
        count++;

        System.out.println("请输入用户名：");
        String username = sc.nextLine();

        System.out.println("请输入密码：");
        String password = sc.nextLine();

        if (username.equals(accurateUsername) && password.equals(accuratePassword)) {
            System.out.println("登录成功");
            count = 0;
        } else {
            System.out.println(count == 3 ? "登录失败，账号已被锁定" : "登录失败，还剩下 " + (3 - count) + " 次机会");
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("Day30/src/com/kkcf/iopractice/userinfo.txt"));
        bw.write("username=" + accurateUsername + "&password=" + accuratePassword + "&count=" + count);
        bw.close();
    }
}
