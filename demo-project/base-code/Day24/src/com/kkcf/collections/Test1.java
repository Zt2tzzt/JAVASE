package com.kkcf.collections;

import java.util.ArrayList;
import java.util.Collections;

public class Test1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        boolean flag = Collections.addAll(list, "abc", "cba", "nba", "yt", "o21", "utr");
        if (flag) System.out.println(list);

        Collections.shuffle(list);

        System.out.println(list);
    }
}
