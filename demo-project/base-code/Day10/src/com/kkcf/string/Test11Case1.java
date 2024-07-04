package com.kkcf.string;

public class Test11Case1 {
    public static void main(String[] args) {
        String strTarget = "cdeab";
        String str = "abcde";

        boolean result = check(strTarget, str);
        System.out.println(result);
    }

    public static boolean check(String strTarget, String str) {
        for (int i = 0; i < str.length(); i++) {
            str = rotate(str);

            if (str.equals(strTarget))
                return true;
        }

        return false;
    }

    public static String rotate(String str) {
        char[] chars = str.toCharArray();

        char temp = chars[0];

        for (int i = 0; i < chars.length; i++)
            if (i != chars.length - 1)
                chars[i] = chars[i + 1];
            else
                chars[i] = temp;

        return new String(chars);
    }
}
