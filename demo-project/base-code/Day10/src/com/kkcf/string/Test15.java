package com.kkcf.string;

public class Test15 {
    public static void main(String[] args) {
        String str = "fly me to the moon";

        System.out.println(caculate(str));
    }

    public static int caculate(String str) {
        int result = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            char c = str.charAt(i);

            if (c == ' ')
                return result;
            else
                result++;
        }

        return result;
    }
}
