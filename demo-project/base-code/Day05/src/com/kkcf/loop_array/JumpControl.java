package com.kkcf.loop_array;

public class JumpControl {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue;
            }

            System.out.println("吃第" + i + "个包子");
        }
    }
}
