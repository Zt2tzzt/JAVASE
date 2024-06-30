package com.kkcf.trainning;

import java.util.Random;

public class Test08Demo02 {
    public static void main(String[] args) {
        int[] awards = {2, 588, 888, 1000, 10000};

        Random r = new Random();

        for (int i = 0; i < awards.length; i++) {
            int index = r.nextInt(awards.length);

            int temp = awards[i];
            awards[i] = awards[index];
            awards[index] = temp;
        }

        // 打印数组
        for (int i = 0; i < awards.length; i++)
            System.out.println(awards[i] + " ");
    }
}
