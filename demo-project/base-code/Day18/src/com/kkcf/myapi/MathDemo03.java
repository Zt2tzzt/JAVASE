package com.kkcf.myapi;

public class MathDemo03 {
    public static void main(String[] args) {
        System.out.println("水仙花数有 " + daffodils() + " 个");

        System.out.println(twoDigitExponentiation() > 0 ? "有" : "没有" + "两位的自幂数");

        System.out.println("四叶玫瑰数有 " + fourleafRose() + " 个");

        System.out.println("五角星数有 " + pentagram() + " 个");
    }

    /**
     * 此方法用于，统计两位自幂数的个数
     * @return 两位自幂数的个数
     */
    public static int twoDigitExponentiation() {
        int count = 0;

        for (int i = 10; i <= 99; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;

            if (Math.pow(ge, 2) + Math.pow(shi, 2) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }

    /**
     * 此方法用于，统计水仙花数的个数
     * @return 水仙花数的个数
     */
    public static int daffodils() {
        int count = 0;
        for (int i = 100; i <= 999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;

            if (Math.pow(ge, 3) + Math.pow(shi, 3) + Math.pow(bai, 3) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }

    /**
     * 此方法用于，统计四叶玫瑰数的个数
     * @return 四叶玫瑰数的个数
     */
    public static int fourleafRose() {
        int count = 0;

        for (int i = 1000; i <= 9999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;
            int qian = i / 1000 % 10;

            if (Math.pow(ge, 4) + Math.pow(shi, 4) + Math.pow(bai, 4) + Math.pow(qian, 4) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }

    /**
     * 此方法用于，统计五角星数的个数
     * @return 五角星数的个数
     */
    public static int pentagram() {
        int count = 0;

        for (int i = 10000; i <= 99999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;
            int qian = i / 1000 % 10;
            int wan = i / 10000 % 10;

            if (Math.pow(ge, 5) + Math.pow(shi, 5) + Math.pow(bai, 5) + Math.pow(qian, 5) + Math.pow(wan, 5) == i) {
                count++;
                System.out.println(i);
            }
        }

        return count;
    }
}
