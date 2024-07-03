package com.kkcf.string;

public class Demo04 {
    public static void main(String[] args) {
        String phoneNum = "13812345678";

        String start = phoneNum.substring(0, 3);
        String end = phoneNum.substring(7);

        String result = start + "****" + end;
        System.out.println(result);
    }
}
