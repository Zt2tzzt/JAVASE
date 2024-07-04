package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("kumiko");
        list.add("mayu");
        list.add("kaori");
        list.add("azusa");

        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.print(i == list.size() - 1 ? s : s + ", ");
        }
        System.out.println("]");

    }
}
