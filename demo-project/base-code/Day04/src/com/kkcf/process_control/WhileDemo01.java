package com.kkcf.process_control;

public class WhileDemo01 {
    public static void main(String[] args) {
        double paperThickness = 0.1; // 纸张厚度
        double mountThickness = 8844430; // 珠穆朗玛峰高度

        int count = 0;

        while (paperThickness < mountThickness) {
            count++;
            paperThickness *= 2;
        }

        System.out.println(count); // 27
    }
}
