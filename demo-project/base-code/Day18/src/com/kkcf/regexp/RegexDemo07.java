package com.kkcf.regexp;

public class RegexDemo07 {
    public static void main(String[] args) {
        String s = "小诗诗dqwefqwfqwfwq12312小丹丹dqwefqwfqwfwq12312小惠惠";

        String[] arr = s.split("[\\w&&[^_]]+");

        for (String string : arr) {
            System.out.print(string + " ");
        }
    }
}
