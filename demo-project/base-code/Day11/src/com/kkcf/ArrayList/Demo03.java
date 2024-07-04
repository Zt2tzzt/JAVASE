package com.kkcf.ArrayList;

import java.util.ArrayList;

public class Demo03 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            int s = list.get(i);
            System.out.print(i == list.size() - 1 ? s : s + ", ");
        }
        System.out.println("]");
    }
}
