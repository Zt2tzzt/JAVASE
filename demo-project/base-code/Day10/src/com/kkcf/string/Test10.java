package com.kkcf.string;

import java.util.Scanner;

public class Test10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str;
        while (true) {
            System.out.println("请输入一个字符串：");
            str = sc.next();

            boolean flag = checkString(str);
            if (flag) break;
            else System.out.println("输入的字符串不符合要求，请重新输入！");
        }

        sc.close();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String s = changeRoman(c - '0');

            sb.append(s);
        }

        System.out.println(sb.toString());
    }

    /**
     * 此函数用于，检查输入的字符串是否符合要求
     * @param str
     * @return
     */
    public static boolean checkString(String str) {
        if (str.length() > 9) return false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') return false;
        }

        return true;
    }

    /**
     * 此函数用于，使用查表法，将阿拉伯数字转换为罗马数字
     * @param num 阿拉伯数字
     * @return
     */
    public static String changeRoman(int num) {
        /*String[] romaNum = {"", "Ⅰ", "Ⅱ", "Ⅲ", "Ⅳ", "Ⅴ", "Ⅵ", "Ⅶ", "Ⅷ", "Ⅸ"};
        return romaNum[num];*/
        String str = switch (num) {
            case 0 -> "";
            case 1 -> "Ⅰ";
            case 2 -> "Ⅱ";
            case 3 -> "Ⅲ";
            case 4 -> "Ⅳ";
            case 5 -> "Ⅴ";
            case 6 -> "Ⅵ";
            case 7 -> "Ⅶ";
            case 8 -> "Ⅷ";
            case 9 -> "Ⅸ";
            default -> "";
        };

        return str;
    }
}
