package com.kkcf.trainning;

import java.util.Scanner;

public class Test05 {
    public static void main(String[] args) {
        // 1.键盘录入，获取 6 个评委的分数。
        int[] scores = getScores();

        // 打印 scores 数组中的元素
        for (int i = 0; i < scores.length; i++)
            System.out.print(scores[i] + " ");
        System.out.println();

        // 2. 获取最大值
        int max = getMax(scores);

        // 3. 获取最小值
        int min = getMin(scores);

        // 求和，并减去最大值，最小值，算平均分数
        int sum = 0;

        for (int i = 0; i < scores.length; i++)
            sum += scores[i];

        int avg = (sum - max - min) / (scores.length - 2);
        System.out.println("平均分：" + avg);
    }

    public static int[] getScores() {
        Scanner sc = new Scanner(System.in);

        int[] scores = new int[6];

        for (int i = 0; i < scores.length; ) {
            System.out.println("请输入第 " + (i + 1) + " 个评委的打分：");
            int score = sc.nextInt();

            if (score < 1 || score > 100)
                System.out.println("超出打分范围，请重新输入");
            else {
                scores[i] = score;
                i++;
            }
        }

        sc.close();

        return scores;
    }

    public static int getMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        return max;
    }

    public static int getMin(int[] arr) {
        int min = arr[0];

        for (int i = 1; i < arr.length; i++)
            if (arr[i] < min)
                min = arr[i];

        return min;
    }
}
