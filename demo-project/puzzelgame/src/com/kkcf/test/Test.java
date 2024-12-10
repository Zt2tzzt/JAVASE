package com.kkcf.test;

import com.kkcf.ui.GameJFrame;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random r = new Random();

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        GameJFrame.shuffleArr(r, arr);

        // 打印数组 arr

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + "\t");
        System.out.println();


        int[][] data = new int[4][4];
        for (int i = 0; i < arr.length; i++)
            data[i / 4][i % 4] = arr[i];

        // 打印数组

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++)
                System.out.print(data[i][j] + "\t");
            System.out.println();
        }
    }
}
