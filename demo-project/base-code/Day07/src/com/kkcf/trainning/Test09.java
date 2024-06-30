package com.kkcf.trainning;

import java.util.Random;
import java.util.Scanner;

public class Test09 {
    public static void main(String[] args) {
        int[] awards = createAwards();

        // 打印数组
        for (int i = 0; i < awards.length; i++)
            System.out.print(awards[i] + " ");
        System.out.println();

        int[] userNum = getUserNum(awards);

        // 打印数组
        for (int i = 0; i < userNum.length; i++)
            System.out.print(userNum[i] + " ");
        System.out.println();

        int[] counts = getAwardNum(awards, userNum);
        System.out.println("红球中奖个数 = " + counts[0]);
        System.out.println("蓝球中奖个数 = " + counts[1]);

        if (counts[0] == 6 && counts[1] == 1) {
            System.out.println("恭喜你，中奖 1000 万！");
        } else if (counts[0] == 6) {
            System.out.println("恭喜你，中奖 500 万了！");
        } else if (counts[0] == 5 && counts[1] == 1) {
            System.out.println("恭喜你，中奖 3000 元！");
        } else if (counts[0] == 5 || (counts[0] == 4 && counts[1] == 1)) {
            System.out.println("恭喜你，中奖 200 元！");
        } else if (counts[0] == 4 || (counts[0] == 3 && counts[1] == 1)) {
            System.out.println("恭喜你，中奖 10 元！");
        } else if (counts[0] == 3 || (counts[0] == 2 && counts[1] == 1)) {
            System.out.println("恭喜你，中奖 5 元！");
        } else {
            System.out.println("很遗憾，没有中奖！");
        }
    }


    /**
     * 此方法用于，随机生成中奖号码
     *
     * @return
     */
    public static int[] createAwards() {
        // 1.随机生成中奖号码
        Random r = new Random();

        int[] awards = new int[7];

        for (int i = 0; i < awards.length; i++) {
            if (i < awards.length - 1) {
                int num = r.nextInt(32) + 1;

                if (!isExist(awards, num))
                    awards[i] = num;
                else
                    i--;
            } else {
                awards[i] = r.nextInt(15) + 1;
            }
        }

        return awards;
    }

    /**
     * 此方法用于，判断数组中是否存在该元素
     *
     * @param arr   数组
     * @param award 待判断的元素
     * @return
     */
    public static boolean isExist(int[] arr, int award) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == award) return true;

        return false;
    }

    /**
     * 此方法用于，获取用户输入的号码
     *
     * @param awards 随机生成的中奖号码
     * @return
     */
    public static int[] getUserNum(int[] awards) {
        // 2.用户输入中奖号码
        Scanner sc = new Scanner(System.in);

        int[] userNum = new int[7];

        for (int i = 0; i < awards.length; ) {
            if (i < awards.length - 1) {
                System.out.println("请输入第 " + (i + 1) + " 个红球号码（范围 1-33）：");
                int num = sc.nextInt();

                if (num < 1 || num > 33) {
                    System.out.println("该号码不符合范围，请重新输入！");
                    continue;
                }

                if (isExist(userNum, num)) {
                    System.out.println("该号码已存在，请重新输入！");
                    continue;
                }

                userNum[i] = num;
                i++;
            } else {
                System.out.println("请输入第 " + 1 + "个蓝球号码（范围 1-16）：");
                int num = sc.nextInt();

                if (num < 1 || num > 16) {
                    System.out.println("该号码不符合范围，请重新输入！");
                    continue;
                }

                userNum[i] = num;
                i++;
            }
        }

        sc.close();

        return userNum;
    }

    /**
     * 此方法用于，获取中奖号码和用户输入号码匹配的个数
     *
     * @param awards     随机生成的中奖号码
     * @param userAwards 用户输入的中奖号码
     * @return
     */
    public static int[] getAwardNum(int[] awards, int[] userAwards) {
        int[] counts = {0, 0};

        for (int i = 0; i < userAwards.length; i++) {
            int userAward = userAwards[i];

            for (int j = 0; j < awards.length; j++) {
                if (i < userAwards.length - 1 && userAward == awards[j]) {
                    counts[0]++;
                    break;
                }

                if (i == userAwards.length - 1 && userAward == awards[j])
                    counts[1]++;
            }
        }

        return counts;
    }
}
