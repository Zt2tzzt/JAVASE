package com.kkcf.trainning;

import java.util.Random;

public class Test08 {
    public static void main(String[] args) {
        int[] awards = {2, 588, 888, 1000, 10000};

        int[] randomaward = new int[5];

        Random r = new Random();

        for (int i = 0; i < randomaward.length; ) {
            int index = r.nextInt(awards.length);

            if (!isExist(randomaward, awards[index])) {
                randomaward[i] = awards[index];
                i++;
            }
        }

        for (int i = 0; i < randomaward.length; i++) {
            System.out.println(randomaward[i] + " ");
        }
    }

    public static boolean isExist(int[] arr, int award) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == award) return true;

        return false;
    }
}
