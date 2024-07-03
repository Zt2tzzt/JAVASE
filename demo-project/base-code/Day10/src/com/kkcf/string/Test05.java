package com.kkcf.string;

import java.util.Scanner;

public class Test05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int money;
        while (true) {
            System.out.println("请输入一个金额：");
            int i = sc.nextInt();

            if (i >= 0 && i <= 9999999) {
                money = i;
                break;
            } else {
                System.out.println("输入的金额无效");
            }
        }

        String moneyStr = "";
        while (money > 0) {
            int num = money % 10;
            moneyStr = getCapitelNumber(num) + moneyStr;
            money /= 10;
        }

        // 在前面补“零”
        int count = 7 - moneyStr.length();
        for (int i = 0; i < count; i++)
            moneyStr = "零" + moneyStr;

        String[] arr = {"佰", "拾", "万", "仟", "佰", "拾", "元"};
        String result = "";
        for (int i = 0; i < moneyStr.length(); i++) {
            char c = moneyStr.charAt(i);
            result += c + arr[i];
        }

        System.out.println(result);
    }

    /**
     * 此函数用于，使用”查表法获取大写数字
     * @param num
     * @return
     */
    public static String getCapitelNumber(int num) {
        String[] arr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        return arr[num];
    }

}
