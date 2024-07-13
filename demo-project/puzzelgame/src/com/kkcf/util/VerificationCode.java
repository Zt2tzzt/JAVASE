package com.kkcf.util;

import java.util.Random;

public class VerificationCode {
    public static String getVerificationCode() {
        char[] letters = new char[52];

        for (int i = 0; i < 26; i++) {
            letters[i] = (char) ('a' + i);
            letters[26 + i] = (char) ('A' + i);
        }

        int[] nums = new int[10];

        for (int i = 0; i < nums.length; i++)
            nums[i] = i;

        // 随机生成四个字母
        Random r = new Random();
        char[] chs = new char[5];
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(letters.length);
            chs[i] = letters[index];
        }

        // 随即生成一个数字
        chs[4] = (char) ('0' + nums[r.nextInt(nums.length)]);

        int index = r.nextInt(chs.length);
        char temp = chs[4];
        chs[4] = chs[index];
        chs[index] = temp;

        // 将字符数组，转为字符串
        return new String(chs);
    }
}
